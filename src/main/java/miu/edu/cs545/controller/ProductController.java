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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;

@Controller
@RequestMapping("/admin/")
@SessionAttributes({ "product" })
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

        if(id==null) model.addAttribute("product", new Product());
        else  model.addAttribute("product", productService.getById(id));

        return "admin/product";
    }

    @GetMapping("products")
    public String products(Model model){

        Pageable sortedByName =
                PageRequest.of(0, 100, Sort.by("createdDate").descending());

        Page<Product> productOptional =  productService.paging(sortedByName);

        model.addAttribute("product", productOptional);

        return "admin/products";
    }

    //@Value("${upload.path}") // upload.path is in application.propeties
    //private String path;

    @PostMapping("product")
    public String save(@ModelAttribute("product") Product product, SessionStatus status, HttpServletRequest request){

        //Upload File to azure blobAzure
        BlobAzure blobAzure=new BlobAzure();
        String path = blobAzure.Upload(product);
        product.setPhoto(path);

        // Can't do like this
//        Seller seller =new Seller();
//        seller.setUsername("luannguyen"); //request.getUserPrincipal().getName()
//        product.setSeller(seller);

        Seller seller = (Seller)accountService.getByUsername("luannguyen");

        Category category = categoryService.getById(1).get();
        //category.setId(1);
        product.setCategory(category);

        product.setStatus(ProductStatus.New);

        product.setCreatedDate(new Date());

        productService.save(product);

        status.setComplete();

        return "redirect:/admin/products";
    }


    @PostMapping("product/delete")
    public String delete(@RequestParam(name = "id") Integer id){

        //Product product=new Product();
        // product.setId(id);
        //productService.getById(id);
        Product product = productService.getById(id).get();
        try {

            productService.delete(productService.getById(id).get());
            return "redirect:/admin/products";
        }
        catch(Exception ex){
            throw  new ProductException("Error: "+product.getName()+" has order, We can not delete");
        }



    }


}
