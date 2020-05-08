package miu.edu.cs545.controller;

import miu.edu.cs545.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class RegisterController {

    @GetMapping("/reg")
    public String doRegistration(@ModelAttribute(value="account") Account account){
        return "user/registration";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute(value="account") Account account){
        return "user/profile";
    }


    @PostMapping("/saveAccount")
    public String saveAccount(@ModelAttribute(value="account") Account account){
        //save account to DB

        return "/user/user";
    }

    @GetMapping("/listReg")
    public String getListRegistration(Model model){
        //Get list of Seller accounts from DB
        ArrayList<Account> listSellerAcc = new ArrayList<>();
        model.addAttribute("listSellerAcc", listSellerAcc);
        return "/admin/listreg";
    }

    @PostMapping("approveAcc")
    public String approveAccount(@RequestParam(value = "uname") String userName){
        //update the status Approved to the user name
        updateUserStatus(userName, true);//true: approved
        return "/admin/listreg";
    }

    @PostMapping("rejectAcc")
    public String rejectAccount(@RequestParam(value = "uname") String userName){
        //update the status Approved to the user name
        updateUserStatus(userName, false);//false: reject
        return "/admin/listreg";
    }

    private void updateUserStatus(String userName, boolean isApprove) {
        //call service to update the status
    }
}
