package com.Rental.rentalHouse.Controller;

import com.Rental.rentalHouse.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/Book/{id}")
    public ModelAndView bookOrder (@PathVariable("id") Integer HouseId,HttpSession session){
        Integer RentId = (Integer) session.getAttribute("id");
        orderService.bookHouse(HouseId, RentId);
        return new  ModelAndView("/Rent/RequestOrder");
    }
}
