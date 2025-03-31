package com.try_store.demo_try_store.service;

import com.try_store.demo_try_store.dto.BookDto;
import com.try_store.demo_try_store.models.Book;

import java.util.List;

public interface BookService {
    List<BookDto> findAllBooks();
    Book saveBook(BookDto bookDto);
    BookDto findBookById(Long bookId);
    void updateBook(BookDto book);
    void delete(Long bookId);
    List<BookDto> searchBooks(String query);
}
