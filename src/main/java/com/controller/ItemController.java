package com.controller;

import com.dao.CategoryInterface;
import com.dao.ImageInterface;
import com.dao.ItemInterface;
import com.dao.UserInterface;
import com.model.Category;
import com.model.Image;
import com.model.Item;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 3/28/2017.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemInterface itemInterface;
    @Autowired
    private CategoryInterface categoryInterface;
    @Autowired
    private ImageInterface imageInterface;
    @Autowired
    private UserInterface userInterface;
    private Path path;
    @RequestMapping("/products")
    public String items(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userInterface.findByUsername(currentUserName);
            model.addAttribute("user", user);
        }
        model.addAttribute("items", itemInterface.findAll());

        return "Items";
    }
    @PostMapping(value="/products")
    public String addItemToCart(@ModelAttribute("item") Item item){

        List<Item> items = new ArrayList<>();
        Item item1 = itemInterface.findOne(item.getId());
        items.add(item1);
      return "redirect:/products";
    }
    @GetMapping("/products/add")
    public String itemAdd(Model model){
        Item item = new Item();
        model.addAttribute("item", item);
        model.addAttribute("categories",  categoryInterface.findAll());
        return "addItem";
    }

    @PostMapping("/products/add")
    public String itemAdd(@ModelAttribute("item") Item item, Model model) throws IOException {

        itemInterface.save(item);
        List<Image> imgs = new ArrayList<>();
        List<MultipartFile> files = item.getImageMultiparts();
        if (null != files && files.size() > 0)
        {
            for (MultipartFile multipartFile : files) {
                String fileName = multipartFile.getOriginalFilename();
                path = Paths.get("C:/Users/Student/IdeaProjects/shopapp/src/main/resources/static/image/" + item.getId()+ "-" + fileName);
                multipartFile.transferTo(new File(path.toString()));
                Image image = new Image();
                image.setItem(item);
                image.setPath(item.getId() + "-" + fileName);
                imageInterface.save(image);
                imgs.add(image);
            }
            item.setImages(imgs);
            itemInterface.save(item);
        }
        return "redirect:/products";
    }

    @GetMapping("/itemView/{id}")
    public String viewItem(@PathVariable Long id, Model model){
        Item item = itemInterface.findOne(id);
        model.addAttribute("item", item);
        return "itemView";
    }
}
