package com.cerberuz.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cerberuz.crud.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
