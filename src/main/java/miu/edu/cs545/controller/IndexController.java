package miu.edu.cs545.controller;

import miu.edu.cs545.dto.LoginModel;
import miu.edu.cs545.service.AccountService;
import miu.edu.cs545.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;

@Controller
public class IndexController {

    private final ServletContext context;

    private final AccountService accountService;
    @Autowired
    private HomeService homeService;

    @Autowired
    public IndexController(ServletContext context, AccountService accountService) {
        this.context = context;
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String index(Model model) {
        String upload = context.getRealPath("uploads");
        System.out.println(upload);
        model.addAttribute("productlistTop", homeService.getTopProducts());
        model.addAttribute("productlistFlow", homeService.getTopProducts());
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
    public String denyAccess() {
        return "/access-denied";
    }

    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute("loginModel") LoginModel loginModel) {
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
}
