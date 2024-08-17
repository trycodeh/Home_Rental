package com.Rental.rentalHouse.Controller;

import com.Rental.rentalHouse.Model.House;
import com.Rental.rentalHouse.Model.UserOrder;
import com.Rental.rentalHouse.Service.HouseService;
import com.Rental.rentalHouse.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController()
@RequestMapping("/Rent")
public class RentController {

    @Autowired
    private HouseService houseService;
    @Autowired
    private OrderService orderService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("Rent/index");
    }

    @RequestMapping("/listHouse")
    public ModelAndView listHouse() {
        ModelAndView mv = new ModelAndView("Rent/listHouse");
        List<House> list = houseService.GetAll();
        mv.addObject("HouseList", list);
        return mv;
    }

    @RequestMapping("/Detail/{id}")
    public ModelAndView Detail(@PathVariable("id") Integer id) {
        House house = houseService.findHouseDetailById(id);
        ModelAndView mv = new ModelAndView("Rent/detail");
        System.out.println("house path :" + house.getPhotoPath1());
        mv.addObject("house", house);
        return mv;
    }

    @RequestMapping("/GetPath/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Path file = houseService.load(filename);
        Resource resource;
        try {
            resource = new UrlResource(file.toUri());
        } catch (IOException e) {
            throw new RuntimeException("Could not read the file!", e);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
//    public Path load(@PathVariable String filename) {
//        System.out.println("Photo path " + Paths.get(uploadDir).resolve(filename));
//        return Paths.get(uploadDir).resolve(filename);
//    }

    @RequestMapping("/Request")
    public ModelAndView RequestOrder(HttpSession session) {
        ModelAndView mv = new ModelAndView("Rent/RequestOrder");
        Integer RentId = (Integer) session.getAttribute("id");
        List<UserOrder> list = orderService.findBookHouseByUserid(RentId);
        mv.addObject("HouseList", list);
        return mv;
    }

    @RequestMapping("/Search")
    public ModelAndView SearchList(String search) {
        List<House> list = houseService.FindHouseByTag(search);
        System.out.println(list);
        ModelAndView mv = new ModelAndView("Rent/listHouse");
        mv.addObject("HouseList", list);
        return mv;
    }
}
