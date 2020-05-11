package miu.edu.cs545.controller;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.service.AccountService;
import miu.edu.cs545.service.BuyerService;
import miu.edu.cs545.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    private final ServletContext context;

    private final AccountService accountService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private BuyerService buyerService;

    @Autowired
    public IndexController(ServletContext context, AccountService accountService) {
        this.context = context;
        this.accountService = accountService;
    }

    @ModelAttribute("myCart")
    public Cart getMyCart() {
        Cart myCart = new Cart();
        List<OnlineOrder> orders = new ArrayList<>();
        OnlineOrder order1 = new OnlineOrder();
        order1.setOrderno("ORD_1#0001");
        order1.setDateCreate(new Date());
        order1.setDateShipping(new Date());

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

        orders.add(order1);

        myCart.setOrderList(orders);
        return myCart;
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        Principal user= request.getUserPrincipal();
        String username="";
        List<Seller> sellerList=null;
        if(user!=null) {
            username = user.getName();
            sellerList= homeService.getFollowerByBuyer(username);

        }
//        user.getName();
        String upload = context.getRealPath("uploads");
        System.out.println(upload);
        model.addAttribute("productlistTop", homeService.getTopProducts());

        model.addAttribute("productlistFlow", homeService.getFollowerProducts(sellerList));
        return "buyer/home";
    }
    @GetMapping("/products")
    public String products(Model model) {
        String upload = context.getRealPath("uploads");
        System.out.println(upload);
        model.addAttribute("productlistTop", homeService.getTopProducts());
//        List<Seller> sellerList = homeService.getFollowerByBuyer(null);
        model.addAttribute("productlistFlow", homeService.getFollowerProducts(null));
        return "buyer/products";
    }
    @GetMapping("/product")
    public String product(Model model) {
        String upload = context.getRealPath("uploads");
        System.out.println(upload);
        model.addAttribute("productlistTop", homeService.getTopProducts());
//        List<Seller> sellerList = homeService.getFollowerByBuyer(null);
        model.addAttribute("productlistFlow", homeService.getFollowerProducts(null));
        return "buyer/product";
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

    @GetMapping("/checkout")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public String showCheckout() {
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
        Cart myCart = getMyCart();
        model.addAttribute("myCart", myCart);
        return "/buyer/shopping-cart";
    }
}
