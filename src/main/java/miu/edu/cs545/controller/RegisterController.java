package miu.edu.cs545.controller;

import miu.edu.cs545.domain.*;
import miu.edu.cs545.dto.Cart;
import miu.edu.cs545.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@SessionAttributes({"accBuyer", "accSeller"})
public class RegisterController {

    @Autowired
    AccountService accountService;

//    @GetMapping("/reg1")
//    public String doRegistration1(@ModelAttribute(value="account") Account account){
//        return "buyer/registration";
//    }

    @GetMapping("/reg")
    public <T extends Account> String doRegistration(@ModelAttribute(value="account") T account){
        return "buyer/registration";
    }

//    @PostMapping("/saveAccount2")
//    public <T extends Account> String saveAccount2(@Valid @ModelAttribute(value="account") T account,
//                                                  BindingResult result, HttpServletRequest request) {
//        if(result.hasErrors()){
//            return "redirect:/reg";//"buyer/registration";
//        }
//
//        String accountType = request.getParameter("user-type");
//        AccountType accType = AccountType.valueOf(accountType);
//        if(accType == AccountType.Buyer){
//            account.setAccountStatus(AccountStatus.Approved);
//        }
//
//        boolean res = accountService.createAccount(account);
//        if (!res) {
//            result.rejectValue("username", "error.username.dup", "The account was existed");
//            return "redirect:/reg";//"buyer/registration";
//        }
//
//        return "redirect:/";//buyer/home";
//    }

    @PostMapping("/saveAccount")
    public String saveAccount(@Valid @ModelAttribute(value="account") Account account, BindingResult result,
                              HttpServletRequest request, Model model){
//        System.out.println("Account info: " + account);
        if(result.hasErrors()){
            return "buyer/registration";
            //return "redirect:/reg";//"buyer/registration";
        }

        //classify account types
        String accountType = request.getParameter("user-type");
        AccountType accType = AccountType.valueOf(accountType);
        boolean res;

        if(accType == AccountType.Seller){
            Seller accSeller = new Seller(account.getUsername(), account.getPassword(),
                    account.getFirstName(), account.getLastName(), AccountStatus.New,
                    account.getEmail());
            res = accountService.createAccount(accSeller);
        }
        else{
            if(accType == AccountType.Buyer){
                Buyer accBuyer = new Buyer(account.getUsername(), account.getPassword(),
                        account.getFirstName(), account.getLastName(), AccountStatus.Approved,
                        account.getEmail());

                Address address = new Address();
                address.setCity("Temp city");
                address.setState("Temp state");
                address.setStreet("Temp street");
                address.setZipcode("00000");

                accBuyer.setBillingAddress(address);
                res = accountService.createAccount(accBuyer);
            }
            else{
                //admin type, do nothing
                Admin accAdmin = new Admin(account.getUsername(), account.getPassword(),
                        account.getFirstName(), account.getLastName(), AccountStatus.New,
                        account.getEmail());
                res = accountService.createAccount(accAdmin);
            }
        }

        if (!res) {
            result.rejectValue("username", "error.username.dup", "The account was existed");
//            return "redirect:/reg";
            return "buyer/registration";
        }

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String showProfile(/*@Valid*/ /*@ModelAttribute(value="account") Buyer account, */HttpServletRequest request, Model model) {
        Principal loggedUser = request.getUserPrincipal();
        if (loggedUser != null) {
            // Identify Seller role or Buyer role
            if (request.isUserInRole("ROLE_BUYER")) {
                Buyer account = accountService.getByUsername(loggedUser.getName());//get the current login user
                model.addAttribute("accBuyer", account);
                return "buyer/profile";
            }
            else {
                if (request.isUserInRole("ROLE_SELLER")) {
                    Seller account = accountService.getByUsername(loggedUser.getName());//get the current login user
                    model.addAttribute("accSeller", account);
                    return "admin/profile";
                } else {
                    return "buyer/home";
                }
            }
        }
        else{
//            return "buyer/home";
            return "redirect:/login";
        }
    }

    @GetMapping("/profile1")
    public String showProfile1(/*@Valid*/ /*@ModelAttribute(value="account") Buyer account, */HttpServletRequest request, Model model){

        Principal loggedUser = request.getUserPrincipal();
        if(loggedUser != null){
            Buyer account = accountService.getByUsername(loggedUser.getName());//get the current login user
//            Account account1 = accountService.getByUsername1(loggedUser.getName());

//            System.out.println("Before show form, Logged user's address id: " + account.getBillingAddress().getId());
//            request.getSession().setAttribute("account", account);
            model.addAttribute("accBuyer", account);
            return "buyer/profile";
        }
        else {
            return "buyer/login";
        }
    }

    @PostMapping("/saveProfile")
    public String updateProfile(@Valid @ModelAttribute(value="accBuyer") Buyer account, BindingResult bindingResult, SessionStatus status){
        if(bindingResult.hasErrors()){
            return "buyer/profile";//"redirect:/profile";//
        }
        //save the buyer profile to DB
//        System.out.println("After show form, Logged user's address id: " + account.getBillingAddress().getId());
        accountService.saveProfile(account);
        status.setComplete();
        return "redirect:/";//"buyer/home";
    }

    @PostMapping("/saveSellerProfile")
    public String updateSellerProfile(@Valid @ModelAttribute(value="accSeller") Seller account, BindingResult bindingResult, SessionStatus status){
        if(bindingResult.hasErrors()){
            return "admin/profile";//"redirect:/profile";//
        }
        //save the buyer profile to DB
//        System.out.println("After show form, Logged user's address id: " + account.getBillingAddress().getId());
        accountService.createAccount(account);
        status.setComplete();
        return "redirect:/admin/products";
    }

//    @GetMapping("seller/listProduct")
//    public <T extends Account> String goToProductList(@ModelAttribute(value="account") T account){
//        return "admin/products";
//    }

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

    @ModelAttribute("myCart")
    public Cart getMyCart() {
        return new Cart();
    }
}
