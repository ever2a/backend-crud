package com.cerberuz.crud.controllers;

import java.util.List;
import java.util.Optional;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cerberuz.crud.entities.Book;
import com.cerberuz.crud.services.BookServiceImpl;

@RestController
@RequestMapping("/book")
@CrossOrigin("http://localhost:4200/")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestPart("book") Book book, @RequestPart("file") MultipartFile file) {
        try {
            Book saveBook = bookServiceImpl.saveBook(book, file);
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Book> updateBookImage(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {
        Optional<Book> book = bookServiceImpl.getBookById(id);
        if (book.isPresent()) {
            Book updateBook = bookServiceImpl.updateBookImage(file, book.get());
            return new ResponseEntity<>(updateBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        try {
            Book saveBook = bookServiceImpl.updateBook(book);
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookServiceImpl.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookServiceImpl.getBookById(id);

        // Forma clásica
        if (book.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book.get(), HttpStatus.OK);

        // Otra forma
        // return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        //             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws IOException {
        Optional<Book> book = bookServiceImpl.getBookById(id);

        if (book.isPresent()){
            bookServiceImpl.deleteBook(book.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
