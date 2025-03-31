package com.try_store.demo_try_store.controller;

import com.try_store.demo_try_store.dto.BookDto;
import com.try_store.demo_try_store.models.Book;
import com.try_store.demo_try_store.models.UserEntity;
import com.try_store.demo_try_store.security.SecurityUtil;
import com.try_store.demo_try_store.service.BookService;
import com.try_store.demo_try_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    private BookService bookService;
    private UserService userService;

    @Autowired
    public HomeController(BookService bookService, UserService userService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        UserEntity user = new UserEntity();
        List<BookDto> books = bookService.findAllBooks();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("books", books);
        return "home";
    }
}
