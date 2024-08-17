package com.Rental.rentalHouse.Service;

import com.Rental.rentalHouse.Model.User;
import com.Rental.rentalHouse.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void addNewUSer(User u) {
        userRepo.save(u);
    }


    public User CheckUser(String email, String password) {
        User u= userRepo.findByEmailAndPassword(email,password);
        return u;
    }

    public String getOwnerName(int ownerId) {
        User u = userRepo.findById(ownerId).get();
        return u.getName();
    }

    public String getOwnerEmail(int ownerId) {
        User u = userRepo.findById(ownerId).get();
        return u.getEmail();
    }

    public User findUserDetailById(Integer userId) {
        return userRepo.findById(userId).get();
    }
}
