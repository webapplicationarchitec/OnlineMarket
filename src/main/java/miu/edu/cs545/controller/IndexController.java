package miu.edu.cs545.controller;

import miu.edu.cs545.domain.*;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.*;

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
    private final CategoryService categoryService;

    @Autowired
    private HomeService homeService;
    @Autowired
    private BuyerService buyerService;


    @Autowired
    public IndexController(ServletContext context, AccountService accountService, ProductService productService, SellerService sellerService, CategoryService categoryService) {
        this.context = context;
        this.accountService = accountService;
        this.productService = productService;
        this.sellerService = sellerService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("myCart")
    public Cart getMyCart() {
        return new Cart();
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        Principal user = request.getUserPrincipal();
        String username = "";
        List<Seller> sellerList = null;
        List<Product> listtop = productService.getTopProducts();
        List<Product> productListFollow = null;
        if (user != null) {
            username = user.getName();
            Buyer buyer = buyerService.getByUsername(username);

            for (Seller sel : buyer.getFollowerList()) {
                productListFollow.addAll(sel.getListProduct());

            }
            if (productListFollow==null)
                productListFollow = listtop.subList(1, 5);
            else
            if (productListFollow.size() > 8)
                productListFollow = productListFollow.subList(0, 8);

        } else {
            productListFollow = listtop.subList(1, 5);
        }
        if (listtop.size() > 7)
            listtop = listtop.subList(0, 8);

        model.addAttribute("productlistFlow", productListFollow);
        model.addAttribute("productlistTop", listtop);
        model.addAttribute("cats", categoryService.getAll());
//        model.addAttribute("message", " xinchao" );

        return "buyer/home";
    }

    @GetMapping("/products")
    public String products(Model model, HttpServletRequest request) {
        String cat = request.getParameter("cat");
        List<Product> list;
        if (cat != null)
            list = productService.getByCategory(Integer.parseInt(cat));
        else
            list = productService.all();
        model.addAttribute("productlist", list);
        return "buyer/products-test";
    }

    @GetMapping("/product")
    public String product(Model model, HttpServletRequest request) {
        String proid = request.getParameter("pid");
        if (proid == null)
            return "/";
        Product pro = productService.getById(Integer.parseInt(proid)).get();
        model.addAttribute("pro", pro);
        return "buyer/product";
    }

    @GetMapping("/follows")
    public String follow(Model model, HttpServletRequest request) {
        Principal user = request.getUserPrincipal();
        if (user == null) {
            model.addAttribute("message", "Please log in to use this function");
            return "redirect:/";
        }
        String username = user.getName();
        Buyer buyer = buyerService.getByUsername(user.getName());

        String type = request.getParameter("type");
        String susername = request.getParameter("user");
        Seller seller = sellerService.getByUsername(susername);
        if (type == "yes") {
            buyer.getFollowerList().add(seller);

            model.addAttribute("message", "Shop " + seller.getFirstName() + " was inserted to your follower");
        } else {
            buyer.getFollowerList().remove(seller);
            model.addAttribute("message", "Shop " + seller.getFirstName() + " was removed from your follower");
        }

        return "buyer/home";
    }

    @GetMapping("/admin")
    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
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
