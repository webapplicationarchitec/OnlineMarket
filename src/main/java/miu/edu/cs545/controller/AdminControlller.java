package miu.edu.cs545.controller;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.Review;
import miu.edu.cs545.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
@Controller
@RequestMapping("/admin/")
public class AdminControlller {

    @Autowired
    AccountService accountService;


    @GetMapping("registrations")
    public String getListRegistration(Model model){

        model.addAttribute("sellers", accountService.getNewSellerAccount());
        return "/admin/registrations";
    }


    @PostMapping("approveAcc")
    public String approveAccount(@RequestParam(value = "username") String userName){
        System.out.println("userName = " +userName);
        accountService.approveNewAcount(AccountStatus.Approved,userName);
        return "redirect:/admin/registrations";
    }

    @PostMapping("rejectAcc")
    public String rejectAccount(@RequestParam(value = "username") String userName){
        accountService.approveNewAcount(AccountStatus.Rejected,userName);
        return "redirect:/admin/registrations";
    }


    @GetMapping("reviews")
    public String getListReview(Model model){
        //Get list of Seller accounts from DB
        ArrayList<Review> listReview = new ArrayList<>();
        model.addAttribute("listReview", listReview);
        return "/admin/reviews";
    }

    @PostMapping("approveReview")
    public String approveReview(@RequestParam(value = "id") Long id){
        //update the status Approved to the review
       // updateReviewStatus(id, true);//true: approved
        return "redirect:/admin/reviews";
    }

    @PostMapping("rejectReview")
    public String rejectReview(@RequestParam(value = "id") Long id){
        //update the status Approved to the user name
        //updateReviewStatus(id, false);//false: reject
        return "redirect:/admin/reviews";
    }


}
