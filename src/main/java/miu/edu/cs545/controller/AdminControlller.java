package miu.edu.cs545.controller;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.Review;
import miu.edu.cs545.domain.ReviewStatus;
import miu.edu.cs545.service.AccountService;
import miu.edu.cs545.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
@Controller
@RequestMapping("/admin/")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminControlller {

    @Autowired
    AccountService accountService;

    @Autowired
    ReviewService reviewService;


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
        model.addAttribute("reviews", reviewService.getNewReviews());
        return "/admin/reviews";
    }

    @PostMapping("approveReview")
    public String approveReview(@RequestParam(value = "id") Integer id){
        reviewService.approveNewReview(ReviewStatus.Approved,id);
        return "redirect:/admin/reviews";
    }

    @PostMapping("rejectReview")
    public String rejectReview(@RequestParam(value = "id") Integer id){
        reviewService.approveNewReview(ReviewStatus.Rejected,id);
        return "redirect:/admin/reviews";
    }





}
