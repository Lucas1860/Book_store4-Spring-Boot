package com.try_store.demo_try_store.controller;

import com.try_store.demo_try_store.dto.BookDto;
import com.try_store.demo_try_store.models.Book;
import com.try_store.demo_try_store.models.UserEntity;
import com.try_store.demo_try_store.models.CartItem;
import com.try_store.demo_try_store.security.SecurityUtil;
import com.try_store.demo_try_store.service.BookService;
import com.try_store.demo_try_store.service.CartService;
import com.try_store.demo_try_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Model model) {
        String username = SecurityUtil.getSessionUser();
        List<CartItem> cartItems = cartService.getUserCart(username);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId) {
        String username = SecurityUtil.getSessionUser();
        cartService.addToCart(username, bookId);
        return "redirect:/books/{bookId}";  
    }

    @GetMapping("/remove/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId) {
        String username = SecurityUtil.getSessionUser();
        cartService.removeFromCart(username, cartItemId);
        return "redirect:/cart";
    }
}