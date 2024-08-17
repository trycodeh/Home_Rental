package com.Rental.rentalHouse.Controller;

import com.Rental.rentalHouse.Model.House;
import com.Rental.rentalHouse.Model.UserOrder;
import com.Rental.rentalHouse.Service.HouseService;
import com.Rental.rentalHouse.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@RestController()
@RequestMapping("/Owner")
public class OwnerController {

    @Autowired
    private HouseService houseService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("Owner/index");
    }
    @RequestMapping("/listHouse")
    public ModelAndView addHouse(HttpSession session){
        ModelAndView mv= new ModelAndView("Owner/listHouse");
        Integer OwnerId=(Integer) session.getAttribute("id");
        List<House> house= houseService.findHouseById(OwnerId);
        mv.addObject("HouseList", house);
        return mv;
    }
    @RequestMapping("/addNew")
    public ModelAndView addNew(){
        return new ModelAndView("Owner/addHouse");
    }
    @PostMapping("/addHouse")
    public ModelAndView addNewHouse(String address, String tags, int price, @RequestParam("photo1") MultipartFile file1,@RequestParam("photo2") MultipartFile file2,@RequestParam("photo3") MultipartFile file3,HttpSession session ){
        ModelAndView mv= new ModelAndView("redirect:/Owner/listHouse");
        try{
            Integer OwnerId=(Integer) session.getAttribute("id");
            houseService.save(address,tags, price, file1,file2,file3,OwnerId);
        }catch (IOException e){
            mv.addObject("message", "failed to submit");
        }
        return mv;
    }
    @GetMapping("/Delete/{id}")
    public ModelAndView DeleteUser(@PathVariable("id") Integer id){
            houseService.removeById(id);
            return new ModelAndView("redirect:/Owner/listHouse");
    }
    @GetMapping("/Edit/{id}")
    public ModelAndView EditUser(@PathVariable Integer id){
       ModelAndView mv = new ModelAndView("/Owner/EditHouse");
       House house= houseService.findHouseDetailById(id);
       mv.addObject("house",house);
        return mv;
    }
    @GetMapping("/Request")
    public ModelAndView SeenAllRequest(HttpSession session){
        ModelAndView mv = new ModelAndView("Owner/Request");
        Integer OwnerId=(Integer) session.getAttribute("id");
        List<UserOrder> list= orderService.findAllRequestHouseById(OwnerId);
        mv.addObject("houseList",list);
        return mv;
    }
    @PostMapping("/addHouse/{id}")
    public ModelAndView editHouse( @PathVariable("id") Integer id,String address, String tags, Integer price, @RequestParam("photo1") MultipartFile file1,@RequestParam("photo2") MultipartFile file2,@RequestParam("photo3") MultipartFile file3,HttpSession session ){
        ModelAndView mv= new ModelAndView("redirect:/Owner/listHouse");
        try{
            Integer OwnerId=(Integer) session.getAttribute("id");
            houseService.EditDetail(address,tags, price, file1,file2,file3,OwnerId,id);
        }catch (IOException e){
            mv.addObject("message", "failed to submit");
        }
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView("Login");
    }

}
