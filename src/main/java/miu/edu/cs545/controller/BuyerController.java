package miu.edu.cs545.controller;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.service.AccountService;
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
import java.util.*;

@Controller
@SessionAttributes("myCart")
public class BuyerController {

    private final ServletContext context;

    private final AccountService accountService;
    private final ProductService productService;
    private final SellerService sellerService;

    @Value("${app.default.tax}")
    private Double taxRate;

    @Value("${app.default.shippingFee}")
    private Double shippingFee;

    @Autowired
    public BuyerController(ServletContext context, AccountService accountService, ProductService productService, SellerService sellerService) {
        this.context = context;
        this.accountService = accountService;
        this.productService = productService;
        this.sellerService = sellerService;
    }

    @ModelAttribute("myCart")
    public Cart getMyCart() {
        return new Cart();
    }

    @GetMapping("/product-test")
    public String pTest(){
        return "buyer/products-test";
    }

    @GetMapping(value = {"/_default"})
    public String redirectAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (request.isUserInRole("ROLE_SELLER")) {
            return "redirect:/seller/orders";
        }
        return "redirect:/";
    }

    @GetMapping("/check-out/{seller}")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public String showCheckout(@PathVariable(name = "seller") String seller) {
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
    Integer addToCart(@PathVariable(name = "productId") Integer productId, Model model) {
        Cart cart = (Cart) model.asMap().get("myCart");
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
                }

                orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQty(0);
                orderDetail.setSellPrice(product.getPrice());

                detailList.add(orderDetail);
            } else {
                return 0;
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

        return 1;
    }
}
