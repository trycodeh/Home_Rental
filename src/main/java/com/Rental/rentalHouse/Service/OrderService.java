package com.Rental.rentalHouse.Service;

import com.Rental.rentalHouse.Model.House;
import com.Rental.rentalHouse.Model.User;
import com.Rental.rentalHouse.Model.UserOrder;
import com.Rental.rentalHouse.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private HouseService houseService;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserService userService;

    public void bookHouse(Integer houseId, Integer userId) {
        House house = houseService.findHouseDetailById(houseId);
        User user = userService.findUserDetailById(userId);
        UserOrder  order= new UserOrder(house.getOwnerId(), house.getOwnerName(),house.getOwnerEmail(),houseId,house.getAddress(),house.getPrice(),user.getId(),user.getName(),user.getEmail());
        orderRepo.save(order);
    }

    public List<UserOrder> findAllRequestHouseById(Integer ownerId) {
        return orderRepo.findHouseByOwnerId(ownerId);
    }

    public List<UserOrder> findBookHouseByUserid(Integer userId) {
        return orderRepo.findAllByUserId(userId);
    }
}
