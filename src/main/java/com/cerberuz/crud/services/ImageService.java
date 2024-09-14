package com.cerberuz.crud.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.cerberuz.crud.entities.Image;

public interface ImageService {

    Image uploadImage(MultipartFile file) throws IOException;
    void deleteImage(Image image) throws IOException;
}
