package miu.edu.cs545.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin/index";
    }

    @GetMapping("/user")
    public String user(){
        return "user/user";
    }
}
