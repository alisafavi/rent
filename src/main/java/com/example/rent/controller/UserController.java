package com.example.rent.controller;

import com.example.rent.model.User;
import com.example.rent.service.FileStorageService;
import com.example.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/FU")
    public void createUser(){

        User user = new User();
        user.setUserName("admin5");
        user.setFirstName("ali");
        user.setLastName("safavi");
        user.setRoles("ADMIN");
        user.setActive(true);
        user.setPassword("12345");

        userService.createUser(user);
    }


    @GetMapping("/register")
    public String registerForm(Model model) throws IOException {

//        String url = MvcUriComponentsBuilder.fromMethodName(UserController.class,"serveFile","unknown.png").build().toString();
//        model.addAttribute("profImg",url);
        model.addAttribute("User",new User());
        model.addAttribute("action","/register");
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User user, @RequestParam MultipartFile profImg) {
        user.setActive(true);
        user.setRoles("USER");
        if (profImg.isEmpty())
            user.setProfImage("unkonow.png");
        else
            user.setProfImage(profImg.getOriginalFilename());
        fileStorageService.storeFile(profImg);
        userService.createUser(user);
        return "login";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = fileStorageService.loadFileAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/update_user")
    public String UpdateUser(Model model) throws IOException {
        User user ;
        if (userService.getAuthenticateUser() != null){
            user=userService.getAuthenticateUser();
            String url = MvcUriComponentsBuilder.fromMethodName(UserController.class,"serveFile",user.getProfImage()).build().toString();
            model.addAttribute("profImg",url);
        }else throw new UsernameNotFoundException("User does not exist");
        model.addAttribute("User",user);
        model.addAttribute("action","/update_user");
        return "register";
    }

    @PostMapping("/update_user")
    public String UpdateUser(@ModelAttribute User user, @RequestParam MultipartFile profImg) {
        user.setActive(true);
        user.setRoles("USER");
        user.setProfImage(profImg.getOriginalFilename());
        userService.updateUser(user);
        if (!profImg.isEmpty())
            fileStorageService.storeFile(profImg);
        return "profile";
    }

    @GetMapping("/error")
    public void error(){
        System.out.println("err");
    }

}
