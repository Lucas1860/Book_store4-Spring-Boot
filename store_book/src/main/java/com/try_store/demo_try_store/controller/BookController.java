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
public class BookController {
    private BookService bookService;
    private UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        UserEntity user = new UserEntity();
        List<BookDto> books = bookService.findAllBooks();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("books", books);
        return "books-list";
    }

    @GetMapping("/books/{bookId}")
    public String bookDetail(@PathVariable("bookId") long bookId, Model model) {
        UserEntity user = new UserEntity();
        BookDto bookDto = bookService.findBookById(bookId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("book", bookDto);
        return "books-detail";
    }

    @GetMapping("/books/new")
    public String createBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books-create";
    }

    @GetMapping("/books/{bookId}/delete")
    public String deleteBook(@PathVariable("bookId")Long bookId) {
        bookService.delete(bookId);
        return "redirect:/books";
    }

    
     @GetMapping("/books/search")
    public String searchBook(@RequestParam(value = "query") String query, Model model) {
        UserEntity user = new UserEntity();
        List<BookDto> books = bookService.searchBooks(query);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("books", books);
        return "books-list";
    }
     

    @PostMapping("/books/new")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("book", bookDto);
            return "books-create";
        }
        bookService.saveBook(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/books/{bookId}/edit")
    public String editBookForm(@PathVariable("bookId") Long bookId, Model model) {
        BookDto book = bookService.findBookById(bookId);
        model.addAttribute("book", book);
        return "books-edit";
    }
    @PostMapping("/books/{bookId}/edit")
    public String updateBook(@PathVariable("bookId") Long bookId,
                             @Valid @ModelAttribute("book") BookDto book,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("book", book);
            return "books-edit";
        }
        book.setId(bookId);
        bookService.updateBook(book);
        return "redirect:/books";
    }
}
