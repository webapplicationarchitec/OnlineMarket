package miu.edu.cs545.controller;

import miu.edu.cs545.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/")
public class SellerController {

    @Autowired
    OrderService orderService;

    @GetMapping("orders")
    public String orders(Model model){
        Pageable sortedByDateCreate =
                PageRequest.of(0, 100, Sort.by("dateCreate").descending());

        model.addAttribute("orders", orderService.paging(sortedByDateCreate));
        return "/admin/orders";
    }

}
