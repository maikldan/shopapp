package com.controller;

import com.dao.ItemInterface;
import com.dao.UserInterface;
import com.model.Cart;
import com.model.Item;
import com.model.User;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Student on 3/29/2017.
 */

@Controller
public class CartController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CartController.class);

    @Autowired
    ItemInterface itemInterface;

    @Autowired
    private Cart cart;
    @Autowired
    UserInterface userInterface;
    @RequestMapping(value = "/cart/add/{id}")
    public String addToCart(@PathVariable("id") Long id, @RequestHeader("referer") String referedFrom){

        Item item = itemInterface.findOne(id);
        cart.addItem(item, (long) 1);
        System.out.println("Adding product ro cart " + item.getName());
        return "redirect:" + referedFrom;
    }
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(Model model){
        Double totalCost = cart.getTotalCost();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userInterface.findByUsername(currentUserName);
            model.addAttribute("user", user);
        }
        model.addAttribute(cart);
        model.addAttribute("totalCost", totalCost);
        return "cart";
    }

//    @RequestMapping(value = "/cart/placeOrder", method = RequestMethod.POST)
//    public String placeOrder(HttpSession session){
//        if (cart.getContents().isEmpty()){
//            return "redirect:/cart";
//        }
//        else {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (!(authentication instanceof AnonymousAuthenticationToken)) {
//                String currentUserName = authentication.getName();
//                User user = userInterface.findByUsername(currentUserName);
//                purchaseService.savePurchase(cart.getContents(), user);
//                cart.clearCart();
//            }
//            return "redirect:/cart";
//        }
//    }
}
