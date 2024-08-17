package com.Rental.rentalHouse.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class House {

    @Id
    @GeneratedValue
    private int id;
    private String OwnerName;
    private int OwnerId;
    private String address;
    private String tags;
    private int price;
    private String OwnerEmail;
    private String photoPath1;
    private String photoPath2;
    private String photoPath3;

    public House(String ownerName, int ownerId, String address, String tags, int price, String ownerEmail, String photoPath1, String photoPath2, String photoPath3) {
        OwnerName = ownerName;
        OwnerId = ownerId;
        this.address = address;
        this.tags = tags;
        this.price = price;
        OwnerEmail = ownerEmail;
        this.photoPath1 = photoPath1;
        this.photoPath2 = photoPath2;
        this.photoPath3 = photoPath3;
    }

    public House(int id, String ownerName, int ownerId, String address, String tags, int price, String ownerEmail, String photoPath1, String photoPath2, String photoPath3) {
        this.id = id;
        OwnerName = ownerName;
        OwnerId = ownerId;
        this.address = address;
        this.tags = tags;
        this.price = price;
        OwnerEmail = ownerEmail;
        this.photoPath1 = photoPath1;
        this.photoPath2 = photoPath2;
        this.photoPath3 = photoPath3;
    }
}
