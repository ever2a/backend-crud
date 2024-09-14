package com.cerberuz.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cerberuz.crud.entities.Image;

@Repository
public interface ImageRespository extends JpaRepository<Image, Long> {
    
}
