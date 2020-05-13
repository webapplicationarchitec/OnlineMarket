package miu.edu.cs545.controller;

import miu.edu.cs545.domain.Buyer;
import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.dto.CheckOutModel;
import miu.edu.cs545.service.AccountService;
import miu.edu.cs545.service.BuyerService;
import miu.edu.cs545.service.ProductService;
import miu.edu.cs545.service.SellerService;
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

    @Value("${app.default.tax}")
    private Double taxRate;

    @Value("${app.default.shippingFee}")
    private Double shippingFee;

    @Autowired
    public BuyerController(ServletContext context, AccountService accountService, ProductService productService, SellerService sellerService, BuyerService buyerService) {
        this.context = context;
        this.accountService = accountService;
        this.productService = productService;
        this.sellerService = sellerService;
        this.buyerService = buyerService;
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
        model.addAttribute("model", checkOutModel);

        return "buyer/checkout";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/buyer/login";
    }

    @GetMapping("/shopping-cart")
    public String showCart(Model model) {
        return "/buyer/shopping-cart";
    }

    @PostMapping("/add-to-cart/{productId}")
    public @ResponseBody
    Integer addToCart(@PathVariable(name = "productId") Integer productId, Model model, @ModelAttribute(name = "myCart") Cart cart) {
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
                    order.setTax(0.00);
                    order.setShippingFee(shippingFee);
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

        Double newTax = order.getTax() + product.getPrice() * taxRate;
        Double newTotal = order.getTotal() + product.getPrice() + product.getPrice() * taxRate;
        order.setTax(newTax);
        order.setTotal(newTotal);

        Integer newQty = orderDetail.getQty() + 1;
        orderDetail.setQty(newQty);

        if (!alreadyInCart) {
            cart.getOrderList().put(product.getSeller().getUsername(), order);
        }

        cart.setTotal(cart.getTotal() + 1);
        return cart.getTotal();
    }

    @PostMapping("/checkout")
    public String proceedCheckout(Model model) {

        return "redirect:/";
    }
}
