package miu.edu.cs545.controller;

import miu.edu.cs545.domain.*;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.service.*;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.IOException;
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
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@SessionAttributes("myCart")
public class IndexController {

    private final ServletContext context;

    private final AccountService accountService;

    private final ProductService productService;
    private final SellerService sellerService;
    private final CategoryService categoryService;
    private final ReviewService reviewService;

    @Autowired
    private HomeService homeService;
    @Autowired
    private BuyerService buyerService;


    @Autowired
    public IndexController(ServletContext context, AccountService accountService, ProductService productService, SellerService sellerService, CategoryService categoryService,ReviewService reviewService) {
        this.context = context;
        this.accountService = accountService;
        this.productService = productService;
        this.sellerService = sellerService;
        this.categoryService = categoryService;
        this.reviewService=reviewService;
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
            if (productListFollow == null)
                productListFollow = listtop.subList(1, 1);
            else if (productListFollow.size() > 8)
                productListFollow = productListFollow.subList(0, 8);

        } else {
            productListFollow = listtop.subList(1, 1);
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
        if (proid == null) {
            proid = "106";
//            return "redirect:/buyer/product";
        }
        Product pro = productService.getById(Integer.parseInt(proid)).get();
        List<Review> reviewList=reviewService.getListReviewsByStatusAndProduct(ReviewStatus.Approved,pro);
        if (reviewList==null){
            Review review= new Review();
            review.setId(1);
            review.setComment(" please make a review. ");
            review.setStatus(ReviewStatus.Approved);
            reviewList.add(review);
        }
        System.out.println(reviewList);
        model.addAttribute("pro", pro);
        model.addAttribute("reviewList", reviewList);
        return "buyer/product";
    }

    @GetMapping("/productReview")
    public String productReview(Model model, HttpServletRequest request) {
        String proid = request.getParameter("pid");
        if (proid == null) {
            proid = "106";
//            return "redirect:/buyer/product";
        }
        Product pro = productService.getById(Integer.parseInt(proid)).get();
        List<Review> reviewList=reviewService.getListReviewsByStatusAndProduct(ReviewStatus.Approved,pro);
        if (reviewList==null){
            Review review= new Review();
            review.setId(1);
            review.setComment(" please make a review. ");
            review.setStatus(ReviewStatus.Approved);
            reviewList.add(review);
        }
        System.out.println(reviewList);
        model.addAttribute("pro", pro);
        model.addAttribute("reviewList", reviewList);
        return "buyer/product-review";
    }
    @PostMapping("/productReview")
    public String SaveReview(@RequestParam(name = "pid")Integer pid, @RequestParam(name = "pmessage") String pmessage,Model model, HttpServletRequest request) {
        System.out.println(pid);
        Principal user = request.getUserPrincipal();
        String username = "";
        String proid = request.getParameter("pid");
        String message = request.getParameter("pmessage");
        if (user != null) {
            username = user.getName();
            Buyer buyer = buyerService.getByUsername(username);
            if (buyer!=null) {
                if (proid != null&&message!=null) {
                    Product pro= productService.getById(Integer.parseInt(proid)).get();
                    if (pro!=null) {
                        Review rev = new Review();
                        rev.setComment(message);
                        rev.setBuyer(buyer);
                        rev.setDateCreate(new Date());
                        pro.getListReview().add(rev);
                        rev.setProduct(pro);
                        rev.setSeller(pro.getSeller());
                        rev.setStatus(ReviewStatus.New);
                        reviewService.addnew(rev);
                        productService.save(pro);

                    }


                }

            }
        }
            if (proid == null) {
                proid = "106";
//            return "redirect:/buyer/product";
            }


        Product pro = productService.getById(Integer.parseInt(proid)).get();
        model.addAttribute("pro", pro);
        return "redirect:/product?pid="+proid;
    }

    @GetMapping("/follows")
    public String follow(Model model, HttpServletRequest request, HttpServletResponse response) {
        Principal user = request.getUserPrincipal();
        String username = "";
        List<Seller> sellerList = null;
        List<Product> listtop = productService.getTopProducts();
        List<Product> productListFollow = null;
        String type = request.getParameter("type");
        String susername = request.getParameter("user");
        if (user != null) {
            username = user.getName();
            Buyer buyer = buyerService.getByUsername(username);

            for (Seller sel : buyer.getFollowerList()) {
                if (sel.getListProduct() != null)
                    productListFollow.addAll(sel.getListProduct());

            }
            if (productListFollow == null)
                productListFollow = listtop.subList(1, 1);
            else if (productListFollow.size() > 8)
                productListFollow = productListFollow.subList(0, 8);

        } else {
            productListFollow = listtop.subList(1, 1);
        }
        if (listtop.size() > 7)
            listtop = listtop.subList(0, 8);

        model.addAttribute("productlistFlow", productListFollow);
        model.addAttribute("productlistTop", listtop);
        model.addAttribute("cats", categoryService.getAll());

        if (user == null) {
            model.addAttribute("message", "Please log in to use this function");
            return "buyer/home";
        }
//        String username1 = user.getName();
        Buyer buyer = buyerService.getByUsername(user.getName());
        Seller seller = sellerService.getByUsername(susername);
        if (type.equals("yes")) {
            boolean result = buyer.getFollowerList().contains(seller);
            if (buyer.getFollowerList().contains(seller) == true) {
                model.addAttribute("message", "Shop " + seller.getFirstName() + " has already followed in your follower");

            } else {
                buyer.getFollowerList().add(seller);
                model.addAttribute("message", "Shop " + seller.getFirstName() + " was inserted to your follower");
            }
        } else {
            buyer.getFollowerList().remove(seller);

            model.addAttribute("message", "Shop " + seller.getFirstName() + " was removed from your follower");
        }
        buyerService.save(buyer);
        for (Seller sel : buyer.getFollowerList()) {
            if (sel.getListProduct() != null)
                productListFollow.addAll(sel.getListProduct());

        }
        if (productListFollow == null)
            productListFollow = listtop.subList(1, 1);
        else if (productListFollow.size() > 8)
            productListFollow = productListFollow.subList(0, 8);
        model.addAttribute("productlistFlow", productListFollow);
//        Response.Redirect(Request.RawUrl);
//        try {
//            return response.sendRedirect("/");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
