package miu.edu.cs545.controller;

import miu.edu.cs545.service.OrderDetailService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/receipt/")
public class ReceiptController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("print/{format}/{orderid}")
    public String orders(@PathVariable(name = "format") String format,@PathVariable(name = "orderid") Integer orderid, Model model) throws FileNotFoundException, JRException {
       String path = orderDetailService.printReceipt(format,orderid);

        return path;

    }

}
