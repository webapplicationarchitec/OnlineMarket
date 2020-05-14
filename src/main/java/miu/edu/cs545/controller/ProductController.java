package miu.edu.cs545.controller;

import miu.edu.cs545.domain.Category;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.ProductStatus;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.exception.ProductException;
import miu.edu.cs545.service.AccountService;
import miu.edu.cs545.service.CategoryService;
import miu.edu.cs545.service.ProductService;
import miu.edu.cs545.utility.BlobAzure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/admin/")
@SessionAttributes({ "product", "categories" })
@Secured({"ROLE_SELLER"})
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ServletContext context;

    @Autowired
    AccountService accountService;

    @Autowired
    CategoryService categoryService;


    @GetMapping("product")
    public String init(@RequestParam(name = "id", required = false) Integer id, Model model){

        if(id==null) { model.addAttribute("product", new Product());}
        else  model.addAttribute("product", productService.getById(id).get());

        model.addAttribute("categories", categoryService.getAll());

        return "admin/product";
    }

    @GetMapping("products")
    public String products(Model model,HttpServletRequest request){

        Pageable sortedByName =
                PageRequest.of(0, 100, Sort.by("createdDate").descending());
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        Page<Product> productOptional =  productService.paging(username ,sortedByName);

        model.addAttribute("product", productOptional);

        return "admin/products";
    }

    //@Value("${upload.path}") // upload.path is in application.propeties
    //private String path;

    @PostMapping("product")
    public String save(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
                       SessionStatus status, HttpServletRequest request){

        if(bindingResult.hasErrors()){
//            return "redirect:/admin/product";
            return "admin/product";
        }

        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        Seller seller = (Seller)accountService.getByUsername(username);
        product.setSeller(seller);


        productService.save(product);
        status.setComplete();
        return "redirect:/admin/products";
    }


    @PostMapping("product/delete")
    public String delete(@RequestParam(name = "id") Integer id){

        //Product product=new Product();
        //product.setId(id);
        //productService.getById(id);
        // Product product = productService.getById(id).get();
        try {

            productService.delete(id);
            return "redirect:/admin/products";
        }
        catch(Exception ex){
            throw  new ProductException("Error: product has order, We can not delete");
        }

    }


}
