package miu.edu.cs545.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer/")
public class HistoryOrderController {

    @RequestMapping("/")
    public  String  getHistoryBuyer(){
         return "";
    }
}
