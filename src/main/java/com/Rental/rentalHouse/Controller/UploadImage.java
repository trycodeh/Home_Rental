package com.Rental.rentalHouse.Controller;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UploadImage {
    //private static final String CLOUDINARY_URL="cloudinary://263868313373183:l4ugAcaw9-yH3LNfvaHLaiaDJTo@dm9eelhx9";

    private Cloudinary cloudinary;
    public Map upload(MultipartFile file) {
        try {
            Map data=  cloudinary.uploader().upload(file.getBytes(),Map.of());
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
