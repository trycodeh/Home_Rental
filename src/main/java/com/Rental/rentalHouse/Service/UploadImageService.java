package com.Rental.rentalHouse.Service;

import com.Rental.rentalHouse.Model.Clo;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadImageService {

    @Autowired
    private Cloudinary cloudinary;
    public String upload(MultipartFile file) {
        try {
            Map data=  cloudinary.uploader().upload(file.getBytes(),Map.of());
            return data.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
