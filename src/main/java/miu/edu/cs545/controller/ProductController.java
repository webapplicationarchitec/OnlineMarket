package miu.edu.cs545.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class ProductController {
    @GetMapping("product")
    public String init(){
        return "admin/product";
    }

    @GetMapping("products")
    public String products(){
        return "admin/products";
    }

    @PostMapping("product")
    public String save(){
        return "admin/products";
    }


    @GetMapping("delete")
    public String delete(){
        return "admin/products";
    }


}
