package com.try_store.demo_try_store.service.impl;

import com.try_store.demo_try_store.dto.BookDto;
import com.try_store.demo_try_store.models.Book;
import com.try_store.demo_try_store.models.CartItem;
import com.try_store.demo_try_store.models.UserEntity;
import com.try_store.demo_try_store.repository.BookRepository;
import com.try_store.demo_try_store.repository.UserRepository;
import com.try_store.demo_try_store.repository.CartRepository;
import com.try_store.demo_try_store.security.SecurityUtil;
import com.try_store.demo_try_store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<CartItem> getUserCart(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return cartRepository.findByUser(user);
    }

    @Override
    public void addToCart(String username, Long bookId) {
        UserEntity user = userRepository.findByUsername(username);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        Optional<CartItem> existingItem = cartRepository.findByUserAndBook(user, book);
        if (existingItem.isEmpty()) {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setBook(book);
            cartRepository.save(cartItem);
        }
    }

    @Override
    public void removeFromCart(String username, Long cartItemId) {
        UserEntity user = userRepository.findByUsername(username);
        CartItem cartItem = cartRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Item not found"));

        if (cartItem.getUser().equals(user)) {
            cartRepository.delete(cartItem);
        }
    }
}