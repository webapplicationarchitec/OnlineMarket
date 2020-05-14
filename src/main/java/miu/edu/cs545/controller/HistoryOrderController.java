package miu.edu.cs545.controller;

import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/buyer/")
@SessionAttributes("myCart")
public class HistoryOrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("historyorders")
    public  String  getHistoryBuyer(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
         model.addAttribute("histories", orderService.getByBuyer(username));
         return "/buyer/historyorder";

    }
    //TODO: should be at other place
    @ModelAttribute("myCart")
    public Cart getMyCart() {
        return new Cart();
    }
}
