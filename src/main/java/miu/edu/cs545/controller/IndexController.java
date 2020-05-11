package miu.edu.cs545.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;

@Controller
public class IndexController {

    private final ServletContext context;

    @Autowired
    public IndexController(ServletContext context) {
        this.context = context;
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

    @GetMapping("/checkout")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public String showCheckout() {
        return "buyer/checkout";
    }

    @GetMapping("/access-denied")
    public String denyAccess(){
        return "/access-denied";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "/buyer/login";
    }

}
