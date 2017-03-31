package com.controller;

import com.dao.ProfileInterface;
import com.dao.UserInterface;
import com.model.Image;
import com.model.Profile;
import com.model.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Student on 3/27/2017.
 */
@Controller
public class UserController {

    @Autowired
    private UserInterface userInterface;
    @Autowired
    private ProfileInterface profileInterface;
    private Path path;
    @RequestMapping("/user")
    public String user(Model model){
        List<User> users = (List<User>) userInterface.findAll();
        model.addAttribute("users", users);
        return "Users";
    }

    @GetMapping("/user/add")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }
    @PostMapping( value = "/user/add")
    public String addUser(@ModelAttribute("user") User user, Model model) throws IOException {
        Profile profile = profileInterface.findOne((long) 2);
        user.setProfile(profile);
        MultipartFile multipartFile = user.getImageMultipart();
        String fileName = multipartFile.getOriginalFilename();
        path = Paths.get("C:/Users/Student/IdeaProjects/shopapp/src/main/resources/static/image/user" + "_" + fileName);
        multipartFile.transferTo(new File(path.toString()));
        user.setPicturePath("user_" + fileName);
        userInterface.save(user);
        return "redirect:/products";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "redirect:/products";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, String error, String logout) {

        return "redirect:/products";
    }
}
