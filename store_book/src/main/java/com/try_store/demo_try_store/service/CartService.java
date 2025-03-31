package com.try_store.demo_try_store.service;

import com.try_store.demo_try_store.dto.BookDto;
import com.try_store.demo_try_store.models.CartItem;
import com.try_store.demo_try_store.models.Book;

import java.util.List;

public interface CartService {
    public List<CartItem> getUserCart(String username);
    public void addToCart(String username, Long bookId);
    public void removeFromCart(String username, Long cartItemId);
}
