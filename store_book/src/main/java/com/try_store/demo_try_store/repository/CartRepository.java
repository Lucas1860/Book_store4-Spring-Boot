package com.try_store.demo_try_store.repository;

import com.try_store.demo_try_store.models.CartItem;
import com.try_store.demo_try_store.models.Book;
import com.try_store.demo_try_store.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(UserEntity user);
    Optional<CartItem> findByUserAndBook(UserEntity user, Book book);
}