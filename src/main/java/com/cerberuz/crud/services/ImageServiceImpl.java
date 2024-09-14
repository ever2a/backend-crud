package com.cerberuz.crud.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cerberuz.crud.entities.Image;
import com.cerberuz.crud.repositories.ImageRespository;

@Service
public class ImageServiceImpl implements ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRespository imageRespository;

    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRespository imageRespository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRespository = imageRespository;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinaryService.upload(file);
        String imageUrl = (String) uploadResult.get("url");
        String imageId = (String) uploadResult.get("public_id");
        Image image = new Image(file.getOriginalFilename(), imageUrl, imageId);
        return imageRespository.save(image);
    }

    @Override
    public void deleteImage(Image image) throws IOException {
        cloudinaryService.delete(image.getImageId());
        imageRespository.deleteById(image.getId());
    }

}
