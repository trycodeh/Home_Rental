package com.Rental.rentalHouse.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class UserOrder {

    @Id
    @GeneratedValue
    private int id;
    private int ownerid;
    private String ownername;
    private String owneremail;
    private int houseid;
    private String address;
    private int price;
    private int userid;
    private String username;
    private String useremail;


    public UserOrder(int ownerid, String ownername, String owneremail, int houseid, String address, int price, int userid, String username, String useremail) {
        this.ownerid = ownerid;
        this.ownername = ownername;
        this.owneremail = owneremail;
        this.houseid = houseid;
        this.address = address;
        this.price = price;
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
    }
}

