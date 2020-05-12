package miu.edu.cs545.controller;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.service.AccountService;
import miu.edu.cs545.service.ProductService;
import miu.edu.cs545.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.*;

@Controller
@SessionAttributes("myCart")
public class IndexController {

    private final ServletContext context;

    private final AccountService accountService;
    private final ProductService productService;
    private final SellerService sellerService;

    @Value("${app.default.tax}")
    private Double taxRate;

    @Value("${app.default.shippingFee}")
    private Double shippingFee;

    @Autowired
    public IndexController(ServletContext context, AccountService accountService, ProductService productService, SellerService sellerService) {
        this.context = context;
        this.accountService = accountService;
        this.productService = productService;
        this.sellerService = sellerService;
    }

    @ModelAttribute("myCart")
    public Cart getMyCart() {
        Cart myCart = new Cart();
        HashMap<String, OnlineOrder> orders = new HashMap<>();

        OnlineOrder order1 = new OnlineOrder();
        order1.setOrderno("ORD_1#0001");
        order1.setDateCreate(new Date());
        order1.setDateShipping(new Date());
        order1.setShippingFee(16.00);
        order1.setTax((84 + 150) * 0.08);
        order1.setTotal(84 + 150 + ((84 + 150) * 0.08) + 16.00);

        Product product = new Product();
        product.setName("Beige knitted elastic runner shoes");
        product.setPrice(84.00);
        product.setPhoto("assets/images/products/table/product-1.jpg");
        product.setPoint(10);

        Product product2 = new Product();
        product2.setName("Beige knitted elastic runner shoes");
        product2.setPrice(75.00);
        product2.setPhoto("assets/images/products/table/product-2.jpg");
        product2.setPoint(5);

        List<OrderDetail> detailList = new ArrayList<>();
        OrderDetail detail1 = new OrderDetail();
        detail1.setProduct(product);
        detail1.setSellPrice(product.getPrice());
        detail1.setQty(2);

        OrderDetail detail2 = new OrderDetail();
        detail2.setProduct(product2);
        detail2.setSellPrice(product2.getPrice());
        detail2.setQty(1);

        detailList.add(detail1);
        detailList.add(detail2);

        order1.setOrderDetailList(detailList);

        myCart.setOrderList(orders);

        Seller seller = new Seller();
        seller.setUsername("microsoft");
        seller.setFirstName("Microsoft");
        seller.setLastName("Inc");
        product.setSeller(seller);
        product2.setSeller(seller);

        orders.put(seller.getUsername(), order1);
        myCart.setOrderList(orders);

        return myCart;
    }

    @GetMapping("/")
    public String index() {
        String upload = context.getRealPath("uploads");
        System.out.println(upload);
        return "buyer/home";
    }

    @GetMapping("/admin")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/user")
    public String user() {
        return "buyer/user";
    }

    @GetMapping("/check-out/{seller}")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public String showCheckout(@PathVariable("seller") String seller, Model model) {
        Cart myCart = (Cart) model.asMap().get("myCart");
        OnlineOrder order = myCart.getOrderList().get(seller);

        model.addAttribute("order", order);
        return "buyer/checkout";
    }

    @GetMapping("/access-denied")
    public String denyAccess() {
        return "/access-denied";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/buyer/login";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "buyer/about";
    }

    @GetMapping("/contact")
    public String showContact() {
        return "buyer/contact";
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
                } else {
                    order = new OnlineOrder();
                    orderDetail = new OrderDetail();
                    order.setTax(0.00);
                    order.setShippingFee(shippingFee);
                    order.setTotal(shippingFee);

                    orderDetail.setProduct(product);
                    orderDetail.setQty(0);
                    orderDetail.setSellPrice(product.getPrice());

                    List<OrderDetail> detailList = new ArrayList<>();
                    detailList.add(orderDetail);
                    order.setOrderDetailList(detailList);
                }
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
