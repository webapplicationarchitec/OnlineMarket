package miu.edu.cs545.controller;

import miu.edu.cs545.domain.OrderStatus;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.service.OrderDetailService;
import miu.edu.cs545.service.OrderService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.security.Principal;

@Controller
@RequestMapping("/buyer/")
@SessionAttributes("myCart")
@PreAuthorize("hasRole('ROLE_BUYER')")
public class HistoryOrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    @RequestMapping("historyorders")
    public  String  getHistoryBuyer(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
         model.addAttribute("histories", orderService.getByBuyer(username));
         return "/buyer/historyorder";

    }

    @PostMapping("order/cancel")
    public String changeStatus(@RequestParam(name = "id") Integer id){
        orderService.updateStatus(OrderStatus.Cancel,id);
        return "redirect:/buyer/historyorders";
    }

    @PostMapping("order/printreceipt")
    public String printReceipt(@RequestParam(name = "id") Integer id) throws FileNotFoundException, JRException {
        String path = orderDetailService.printReceipt("pdf",id);
        return "redirect:"+path;
    }



    //TODO: should be at other place
    @ModelAttribute("myCart")
    public Cart getMyCart() {
        return new Cart();
    }
}
