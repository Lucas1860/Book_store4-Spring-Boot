package com.try_store.demo_try_store.mapper;

import com.try_store.demo_try_store.dto.BookDto;
import com.try_store.demo_try_store.models.Book;

import java.util.stream.Collectors;


public class BookMapper {
    public static Book mapToBook(BookDto book) {
        Book bookDto = Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .photoUrl(book.getPhotoUrl())
                .content(book.getContent())
                .cost(book.getCost())
                .createdBy(book.getCreatedBy())
                .createdOn(book.getCreatedOn())
                .updatedOn(book.getUpdatedOn())
                .build();
        return  bookDto;
    }

    public static BookDto mapToBookDto(Book book) {
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .photoUrl(book.getPhotoUrl())
                .content(book.getContent())
                .cost(book.getCost())
                .createdBy(book.getCreatedBy())
                .createdOn(book.getCreatedOn())
                .updatedOn(book.getUpdatedOn())
                .build();
        return bookDto;
    }
}
