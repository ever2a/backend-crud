package com.cerberuz.crud.services;

import java.util.List;
import java.util.Optional;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.cerberuz.crud.entities.Book;

public interface BookService {

    Book saveBook(Book book, MultipartFile file) throws IOException;
    Book updateBook(Book book);
    List<Book> getBooks();
    Optional<Book> getBookById(Long id);
    void deleteBook(Book book) throws IOException;
    Book updateBookImage(MultipartFile file, Book book) throws IOException;

}
