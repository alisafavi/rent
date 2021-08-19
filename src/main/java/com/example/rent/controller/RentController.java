package com.example.rent.controller;

import com.example.rent.model.Rent;
import com.example.rent.service.ProductService;
import com.example.rent.service.RentService;
import com.example.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/new/{productId}")
    public RedirectView newRent(@PathVariable int productId){
        Rent rent = new Rent();
        rent.setUser(userService.getAuthenticateUser());
        rent.setProduct(productService.findProductById(productId));
        rentService.AddRentDetails(rent);
        return new RedirectView("../../product/all");
    }

    @GetMapping("/delete/{rentId}")
    public RedirectView obsoleteRent(@PathVariable int rentId){
        rentService.delete(rentId);
        return new RedirectView("../../rent/my_rent");
    }

    @GetMapping("/my_rent")
    public String myRent(ModelMap modelMap){
        modelMap.addAttribute("myRents",
                rentService.myRent(userService.getAuthenticateUser()));
        return "pages/my_rent";
    }

}
