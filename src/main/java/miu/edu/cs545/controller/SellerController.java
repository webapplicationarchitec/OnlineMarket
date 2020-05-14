package miu.edu.cs545.controller;

import miu.edu.cs545.domain.OrderStatus;
import miu.edu.cs545.service.OrderDetailService;
import miu.edu.cs545.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/seller/")
@Secured({"ROLE_SELLER"})
public class SellerController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("orders")
    public String orders(Model model,  HttpServletRequest request){
        Pageable sortedByDateCreate =
                PageRequest.of(0, 100, Sort.by("dateCreate").descending());

        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        model.addAttribute("orders", orderService.paging(username,sortedByDateCreate));
        return "/admin/orders";

    }

    @PostMapping("changeStatus")
    public String changeStatus(@RequestParam(name = "status") OrderStatus status, @RequestParam(name = "id") Integer id){

        orderService.updateStatus(status,id);
        return "redirect:/seller/orders";
    }

    @GetMapping("orderdetail")
    public String detail(@RequestParam(name="id") Integer id,Model model){

        System.out.println("id =" + id);

        model.addAttribute("productdetails", orderDetailService.getDetailByOrderId(id));
        return "/admin/detail";

    }

}
