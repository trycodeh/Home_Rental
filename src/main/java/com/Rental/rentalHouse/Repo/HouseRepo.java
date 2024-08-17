package com.Rental.rentalHouse.Repo;

import com.Rental.rentalHouse.Model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepo  extends JpaRepository<House, Integer> {

    @Query(value = "select * from house where owner_id=:id",nativeQuery = true)
    List<House> findAllHouseById(Integer id);

    List<House> findByTagsContaining(String tags);

//    @Modifying
//    @Query(value = "UPDATE house SET address = :address WHERE id = :houseId", nativeQuery = true)
//    void editData(@Param("houseId") Integer houseId, @Param("address") String address);

}
