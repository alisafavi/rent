package com.example.rent.controller;

import com.example.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index()
    {
        return "layout";
    }
//
//    @GetMapping("/fragments/navigation")
//    public String navigation(Model model)
//    {
//        User user = userService.getAuthenticateUser();
//        String url = MvcUriComponentsBuilder.fromMethodName(UserController.class,"serveFile",user.getProfImage()).build().toString();
//        model.addAttribute("profimg",url);
//        return "fragments/navigation";
//    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/l")
    public String l(){
        return "/pages/products";
    }
}
