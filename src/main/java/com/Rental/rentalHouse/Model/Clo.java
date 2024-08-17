package com.Rental.rentalHouse.Model;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Clo {
    @Bean
        public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dm9eelhx9",
                "api_key", "263868313373183",
                "api_secret", "l4ugAcaw9-yH3LNfvaHLaiaDJTo"));
    }
}
