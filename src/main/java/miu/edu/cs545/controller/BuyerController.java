package miu.edu.cs545.controller;

import miu.edu.cs545.domain.*;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.dto.CheckOutModel;
import miu.edu.cs545.dto.RemoveCartModel;
import miu.edu.cs545.exception.OrderCreateException;
import miu.edu.cs545.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
@SessionAttributes("myCart")
public class BuyerController {

    private final ServletContext context;

    private final AccountService accountService;
    private final ProductService productService;
    private final SellerService sellerService;
    private final BuyerService buyerService;
    private final OrderService orderService;
    private final BonusPointService bonusPointService;

    @Value("${app.default.tax}")
    private Double taxRate;

    @Value("${app.default.shippingFee}")
    private Double shippingFee;

    @Autowired
    public BuyerController(ServletContext context, AccountService accountService, ProductService productService
            , SellerService sellerService, BuyerService buyerService, OrderService orderService, BonusPointService bonusPointService) {
        this.context = context;
        this.accountService = accountService;
        this.productService = productService;
        this.sellerService = sellerService;
        this.buyerService = buyerService;
        this.orderService = orderService;
        this.bonusPointService = bonusPointService;
    }

    @ModelAttribute("myCart")
    public Cart getMyCart() {
        return new Cart();
    }

    @GetMapping("/404")
    public String pTest() {
        return "buyer/404";
    }

    @GetMapping("/check-out/{seller}")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public String showCheckout(@PathVariable(name = "seller") String seller, HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        Buyer buyer = buyerService.getByUsername(username);

        if (buyer == null) {
            return "redirect:/404";
        }
        Cart cart = (Cart) model.asMap().get("myCart");
        OnlineOrder order = cart.getOrderList().get(seller);
        if (order == null) {
            return "redirect:/404";
        }

        CheckOutModel checkOutModel = new CheckOutModel();
        checkOutModel.setBuyer(buyer);
        checkOutModel.setOrder(order);

        BonusPoint bonusPoint = bonusPointService.getPoint(new Seller() {{
            setUsername(seller);
        }}, buyer);

        if (bonusPoint != null && bonusPoint.getPoints() > 0) {
            checkOutModel.setPoints(bonusPoint.getPoints());
        } else {
            checkOutModel.setPoints(0);
        }

        model.addAttribute("model", checkOutModel);
        return "buyer/checkout";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest httpServletRequest) {
        return "/buyer/login";
    }

    @GetMapping("/shopping-cart")
    public String showCart(Model model) {
        return "/buyer/shopping-cart";
    }

    @GetMapping("/add-to-cart/{productId}/{qty}")
    public @ResponseBody
    Integer addToCart(@PathVariable(name = "productId") Integer productId, @PathVariable(name = "qty") Integer quantity, Model model, @ModelAttribute(name = "myCart") Cart cart) {
        //Cart cart = (Cart) model.asMap().get("myCart");
        HashMap<String, OnlineOrder> orders = cart.getOrderList();

        OnlineOrder order = null;
        Product product = null;
        OrderDetail orderDetail = null;
        List<OrderDetail> detailList = null;

        Boolean alreadyInCart = false;

        for (Map.Entry item : orders.entrySet()) {
            if (alreadyInCart) {
                break;
            }
            order = (OnlineOrder) item.getValue();
            for (OrderDetail detail : order.getOrderDetailList()) {
                Product temp = detail.getProduct();
                if (productId == temp.getId()) {
                    product = temp;
                    orderDetail = detail;
                    alreadyInCart = true;
                    break;
                }
            }
        }

        if (!alreadyInCart) {
            Optional<Product> opt = productService.getById(productId);
            if (opt.isPresent()) {
                product = opt.get();
                String sellerId = product.getSeller().getUsername();
                orderDetail = new OrderDetail();

                if (cart.getOrderList().containsKey(sellerId)) {
                    order = cart.getOrderList().get(sellerId);
                    detailList = order.getOrderDetailList();
                } else {
                    order = new OnlineOrder();
                    order.setStatus(OrderStatus.New);
                    order.setTax(0.00);
                    order.setShippingFee(shippingFee);
                    order.setShippingAddress("");
                    order.setTotal(shippingFee);
                    detailList = new ArrayList<>();
                    order.setOrderDetailList(detailList);
                    order.setSeller(product.getSeller());
                }

                orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQty(0);
                orderDetail.setSellPrice(product.getPrice());

                detailList.add(orderDetail);
            } else {
                return -1;
            }
        }

        Double newTax = order.getTax() + quantity * product.getPrice() * taxRate;
        Double newTotal = order.getTotal() + (quantity * product.getPrice()) + (quantity * product.getPrice() * taxRate);
        order.setTax(newTax);
        order.setTotal(newTotal);

        Integer newQty = orderDetail.getQty() + quantity;
        orderDetail.setQty(newQty);

        if (!alreadyInCart) {
            cart.getOrderList().put(product.getSeller().getUsername(), order);
        }

        cart.setTotal(cart.getTotal() + quantity);
        return cart.getTotal();
    }

    @GetMapping("/remove-from-cart/{productId}")
    public @ResponseBody
    RemoveCartModel removeFromCart(@PathVariable(name = "productId") Integer productId, Model model) {
        Cart cart = (Cart) model.asMap().get("myCart");

        HashMap<String, OnlineOrder> orders = cart.getOrderList();
        OnlineOrder order = null;
        OrderDetail orderDetail = null;
        List<OrderDetail> detailList = null;
        Product product = null;

        Boolean found = false;
        for (Map.Entry item : orders.entrySet()) {
            if (found) {
                break;
            }

            order = (OnlineOrder) item.getValue();
            for (OrderDetail detail : order.getOrderDetailList()) {
                Product temp = detail.getProduct();
                if (temp.getId() == productId) {
                    product = temp;
                    orderDetail = detail;
                    detailList = order.getOrderDetailList();
                    detailList.remove(detail);
                    Double newTotal = order.getTotal() - ((detail.getQty() * detail.getSellPrice()) + (detail.getQty() * detail.getSellPrice() * taxRate));
                    Double newTax = order.getTax() - (detail.getQty() * detail.getSellPrice() * taxRate);
                    order.setTotal(newTotal);
                    order.setTax(newTax);

                    found = true;
                    break;
                }
            }
        }

        Integer cartItemTotal = cart.getTotal() - orderDetail.getQty();
        Integer orderItemTotal = 0;

        for (OrderDetail detail : order.getOrderDetailList()) {
            orderItemTotal += detail.getQty();
        }

        if (orderItemTotal == 0) {
            orders.remove(product.getSeller().getUsername());
        }

        cart.setTotal(cartItemTotal);
        RemoveCartModel removeCartModel = new RemoveCartModel(cartItemTotal, orderItemTotal, product.getId(), order);
        return removeCartModel;
    }

    @GetMapping("/cart-adjust-qty/{productId}/{qty}")
    public @ResponseBody
    RemoveCartModel adjustQuantity(@PathVariable("productId") Integer productId, @PathVariable("qty") Integer qty, Model model) {
        Cart cart = (Cart) model.asMap().get("myCart");

        HashMap<String, OnlineOrder> orders = cart.getOrderList();
        OnlineOrder order = null;
        OrderDetail orderDetail = null;
        List<OrderDetail> detailList = null;
        Product product = null;

        Integer oldQty = 0;

        Boolean found = false;
        for (Map.Entry item : orders.entrySet()) {
            if (found) {
                break;
            }

            order = (OnlineOrder) item.getValue();
            for (OrderDetail detail : order.getOrderDetailList()) {
                Product temp = detail.getProduct();
                if (temp.getId() == productId) {
                    product = temp;
                    orderDetail = detail;
                    oldQty = orderDetail.getQty();
                    Double newTax = order.getTax() + (qty * product.getPrice() * taxRate) - (oldQty * product.getPrice() * taxRate);
                    Double newTotal = order.getTotal() + (qty * product.getPrice() + (qty * product.getPrice() * taxRate))
                            - (oldQty * product.getPrice() + (oldQty * product.getPrice() * taxRate));
                    order.setTotal(newTotal);
                    order.setTax(newTax);
                    orderDetail.setQty(qty);

                    found = true;
                    break;
                }
            }
        }

        Integer cartItemTotal = cart.getTotal() - oldQty + qty;
        cart.setTotal(cartItemTotal);

        RemoveCartModel removeCartModel = new RemoveCartModel(cartItemTotal, 0, product.getId(), order);
        return removeCartModel;
    }

    @PostMapping("/check-out")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public String proceedCheckout(@ModelAttribute("checkOutModel") CheckOutModel checkOutModel, Model model, HttpServletRequest request) throws OrderCreateException {
        Cart cart = (Cart) model.asMap().get("myCart");
        Principal principal = request.getUserPrincipal();
        String seller = checkOutModel.getOrder().getSeller().getUsername();
        OnlineOrder order = cart.getOrderList().get(seller);
        if (checkOutModel.getOrder().getShippingAddress() != null && !checkOutModel.getOrder().getShippingAddress().trim().equals("")) {
            order.setShippingAddress(checkOutModel.getOrder().getShippingAddress());
        }
        orderService.placeOrder(order, principal.getName(), checkOutModel.getUsedPoints());

        cart.getOrderList().remove(seller);
        Integer orderItemTotal = 0;
        for (OrderDetail detail : order.getOrderDetailList()) {
            orderItemTotal += detail.getQty();
        }
        cart.setTotal(cart.getTotal() - orderItemTotal);

        return "redirect:/buyer/historyorders";
    }
}
