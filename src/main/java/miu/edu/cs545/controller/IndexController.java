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

import miu.edu.cs545.service.BuyerService;
import miu.edu.cs545.service.HomeService;
import miu.edu.cs545.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import java.util.HashMap;
import java.util.List;


@Controller
@SessionAttributes("myCart")
public class IndexController {

    private final ServletContext context;

    private final AccountService accountService;

    private final ProductService productService;
    private final SellerService sellerService;

    @Autowired
    private HomeService homeService;
    @Autowired
    private BuyerService buyerService;


    @Autowired
    public IndexController(ServletContext context, AccountService accountService, ProductService productService, SellerService sellerService) {
        this.context = context;
        this.accountService = accountService;
        this.productService = productService;
        this.sellerService = sellerService;
    }

    @ModelAttribute("myCart")
    public Cart getMyCart() {
        return new Cart();
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        Principal user= request.getUserPrincipal();
        String username="";
        List<Seller> sellerList=null;
        if(user!=null) {
            username = user.getName();
//            sellerList= homeService.getFollowerByBuyer(username);
            //        user.getName();
            model.addAttribute("productlistTop", productService.getTopProducts());//.getTopProducts());
            model.addAttribute("productlistFlow", productService.getFollowerProducts(username));//.getFollowerProducts(sellerList));

        }else {
            model.addAttribute("productlistTop", productService.getTopProducts());
            model.addAttribute("productlistFlow", productService.all());//.getFollowerProducts(sellerList));

        }

        return "buyer/home";
    }
    @GetMapping("/products")
    public String products(Model model,HttpServletRequest request) {
       String cat = request.getParameter("cat");
       List<Product> list;
       if(cat!=null)
           list=productService.getByCategory(Integer.parseInt(cat));
       else
           list=productService.all();
        model.addAttribute("productlist", list);
        return "buyer/products";
    }
    @GetMapping("/product")
    public String product(Model model,HttpServletRequest request) {
        String proid = request.getParameter("pid");
        if(proid==null)
            return "/";
        Product pro = productService.getById(Integer.parseInt(proid)).get();
        model.addAttribute("pro", pro);
        return "buyer/product";
    }

    @GetMapping("/admin")
    @Secured({"ROLE_ADMIN","ROLE_SELLER"})
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/user")
    public String user() {
        return "buyer/user";
    }

    @GetMapping("/access-denied")
    public String denyAccess() {
        return "/access-denied";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "buyer/about";
    }

    @GetMapping("/contact")
    public String showContact() {
        return "buyer/contact";
    }

}
