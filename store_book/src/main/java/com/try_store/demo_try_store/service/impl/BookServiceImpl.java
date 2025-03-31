package com.try_store.demo_try_store.service.impl;

import com.try_store.demo_try_store.dto.BookDto;
import com.try_store.demo_try_store.models.Book;
import com.try_store.demo_try_store.models.UserEntity;
import com.try_store.demo_try_store.repository.BookRepository;
import com.try_store.demo_try_store.repository.UserRepository;
import com.try_store.demo_try_store.security.SecurityUtil;
import com.try_store.demo_try_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.try_store.demo_try_store.mapper.BookMapper.mapToBook;
import static com.try_store.demo_try_store.mapper.BookMapper.mapToBookDto;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map((book) -> mapToBookDto(book)).collect(Collectors.toList());
    }

    @Override
    public Book saveBook(BookDto bookDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Book book = mapToBook(bookDto);
        book.setCreatedBy(user);
        return bookRepository.save(book);
    }

    @Override
    public BookDto findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        return mapToBookDto(book);
    }

    @Override
    public void updateBook(BookDto bookDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Book book = mapToBook(bookDto);
        book.setCreatedBy(user);
        bookRepository.save(book);
    }

    @Override
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookDto> searchBooks(String query) {
        List<Book> books = bookRepository.searchBooks(query);
        return books.stream().map(book -> mapToBookDto(book)).collect(Collectors.toList());
    }
}
