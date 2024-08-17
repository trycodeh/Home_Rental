package com.Rental.rentalHouse.Service;

import com.Rental.rentalHouse.Model.House;
import com.Rental.rentalHouse.Repo.HouseRepo;
import com.Rental.rentalHouse.Repo.OrderRepo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

@Service
public class HouseService {

    //file path
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private UserService userService;
    @Autowired
    private HouseRepo houseRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UploadImageService uploadImage;


    public void save(String address, String tags, int price, MultipartFile file1, MultipartFile file2, MultipartFile file3, int OwnerId) throws IOException {

//        File f= new File(uploadDir);
//
//        // creating file path
//        if(!f.exists()){
//            f.mkdirs();
//        }

        // coping the image
//        String name=file1.getOriginalFilename();
//        String fileName1= UUID.randomUUID().toString() +name.substring(name.lastIndexOf("."));
//        Path path1=Paths.get(uploadDir +File.separator+fileName1);
//        Files.copy(file1.getInputStream(),path1, StandardCopyOption.REPLACE_EXISTING);
//
//        String name1=file1.getOriginalFilename();
//        String fileName2= UUID.randomUUID().toString() +name1.substring(name1.lastIndexOf("."));
//        Path path2 = Paths.get(uploadDir + "/" + fileName2);
//        Files.copy(file2.getInputStream(), path2, StandardCopyOption.REPLACE_EXISTING);
//
//        String name3=file1.getOriginalFilename();
//        String fileName3= UUID.randomUUID().toString() +name3.substring(name3.lastIndexOf("."));
//        Path path3 = Paths.get(uploadDir + "/" + fileName3);
//        Files.copy(file3.getInputStream(), path3, StandardCopyOption.REPLACE_EXISTING);

        //Uploading image to Service
        String fileName1 =uploadImage.upload(file1);
        String fileName2 =uploadImage.upload(file2);
        String fileName3 =uploadImage.upload(file3);

        String ownerName = userService.getOwnerName(OwnerId);
        String ownerEmail= userService.getOwnerEmail(OwnerId);
        House house= new House( ownerName, OwnerId,  address,  tags,  price,ownerEmail,  fileName1,fileName2,fileName3);
        houseRepo.save(house);
    }


    public List<House> findHouseById(Integer id) {
        return houseRepo.findAllHouseById(id);
    }

    public void removeById(Integer id) {
        houseRepo.deleteById(id);
    }

    public List<House> GetAll() {
        return houseRepo.findAll();
    }


    public House findHouseDetailById(Integer id) {
        return houseRepo.findById(id).get();
    }

    public Integer findOwnerByHouseId(Integer houseId) {
        House house = houseRepo.findById(houseId).get();
        return house.getOwnerId();
    }

    public Resource loadFileAsResource(String filename) throws MalformedURLException {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            UrlResource resource =  new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return  (Resource) resource;
            } else {
                throw new RuntimeException("Could not read the file: " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error: " + ex.getMessage());
        }
    }

    public Path load(String filename) {
        return Paths.get(uploadDir).resolve(filename);
    }
    public List<House> FindHouseByTag(String tags){
        return houseRepo.findByTagsContaining(tags);
    }

    public void EditDetail(String address, String tags, Integer price, MultipartFile file1, MultipartFile file2, MultipartFile file3, Integer ownerId,Integer houseId) throws IOException {

        //Creating folder
        File f= new File(uploadDir);

        // creating file path
        if(!f.exists()){
            f.mkdirs();
        }

        // coping the image
        String name=file1.getOriginalFilename();
        String fileName1= UUID.randomUUID().toString() +name.substring(name.lastIndexOf("."));
        Path path1=Paths.get(uploadDir +File.separator+fileName1);
        Files.copy(file1.getInputStream(),path1, StandardCopyOption.REPLACE_EXISTING);

        String name1=file1.getOriginalFilename();
        String fileName2= UUID.randomUUID().toString() +name1.substring(name1.lastIndexOf("."));
        Path path2 = Paths.get(uploadDir + "/" + fileName2);
        Files.copy(file2.getInputStream(), path2, StandardCopyOption.REPLACE_EXISTING);

        String name3=file1.getOriginalFilename();
        String fileName3= UUID.randomUUID().toString() +name3.substring(name3.lastIndexOf("."));
        Path path3 = Paths.get(uploadDir + "/" + fileName3);
        Files.copy(file3.getInputStream(), path3, StandardCopyOption.REPLACE_EXISTING);

        String OwnerName= userService.getOwnerName(ownerId);
        String OwnerEmail= userService.getOwnerEmail(ownerId);
       //houseRepo.editData(houseId,address,price,tags,fileName1,fileName2,fileName3);
       //houseRepo.editData(houseId,address);
        House house= new House(houseId,OwnerName, ownerId, address,tags,price,OwnerEmail,fileName1,fileName2,fileName3);
        houseRepo.save(house);
    }
}
