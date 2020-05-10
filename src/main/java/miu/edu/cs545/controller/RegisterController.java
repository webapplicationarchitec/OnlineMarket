package miu.edu.cs545.controller;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.Review;
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
        return "buyer/registration";
    }

    @GetMapping("/profile")
    public String showProfile(@ModelAttribute(value="account") Account account){
        return "buyer/profile";
    }

    @PostMapping("/saveProfile")
    public String updateProfile(@ModelAttribute(value="account") Account account){
        return "buyer/profile";
    }


    @PostMapping("/saveAccount")
    public String saveAccount(@ModelAttribute(value="account") Account account){
        //save account to DB
        OnlineOrder acc = new OnlineOrder();
        acc.setOrderno("");
        return "/buyer/user";
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

    @GetMapping("/listRev")
    public String getListReview(Model model){
        //Get list of Seller accounts from DB
        ArrayList<Review> listReview = new ArrayList<>();
        model.addAttribute("listReview", listReview);
        return "/admin/listreview";
    }

    @PostMapping("approveRev")
    public String approveReview(@RequestParam(value = "rev_id") Long id){
        //update the status Approved to the review
        updateReviewStatus(id, true);//true: approved
        return "/admin/listreview";
    }

    @PostMapping("rejectRev")
    public String rejectReview(@RequestParam(value = "rev_id") Long id){
        //update the status Approved to the user name
        updateReviewStatus(id, false);//false: reject
        return "/admin/listreview";
    }

    private void updateReviewStatus(Long reviewId, boolean isApprove) {
        //call service to update the status
    }
}
