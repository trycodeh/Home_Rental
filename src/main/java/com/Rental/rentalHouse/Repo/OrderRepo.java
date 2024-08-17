package com.Rental.rentalHouse.Repo;

import com.Rental.rentalHouse.Model.House;
import com.Rental.rentalHouse.Model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo  extends JpaRepository<UserOrder,Integer> {
    @Query(value = "select * from user_order where userid=:userId", nativeQuery = true)
    List<UserOrder> findAllByUserId(Integer userId);

    @Query(value = "select * from user_order where ownerid=:ownerId ",nativeQuery = true)
    List<UserOrder> findHouseByOwnerId(Integer ownerId);
}
