package miu.edu.cs545.controller;

import miu.edu.cs545.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegisterController {

    @GetMapping("/reg")
    public String doRegistration(@ModelAttribute(value="account") Account account){
        return "registration";
    }
}
