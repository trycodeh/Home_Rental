package com.Rental.rentalHouse.Controller;

import com.Rental.rentalHouse.Model.User;
import com.Rental.rentalHouse.Service.UploadImageService;
import com.Rental.rentalHouse.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "Signup";
    }
    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session, Model model) {
        User u = userService.CheckUser(email, password);
        if (u != null) {
            if (u.getType().equals("Owner")) {
                session.setAttribute("id", u.getId());
                return "Owner/index";
            } else {
                session.setAttribute("id", u.getId());
                return "Rent/index";
            }
        } else {
            model.addAttribute("message", "Email Or Password wrong..");
            return "login";
        }
    }

    @GetMapping("/Signup")
    public String signup() {
        return "Signup";
    }

    @PostMapping("/Signup")
    public String Signup(User u, Model model) {
        userService.addNewUSer(u);
            model.addAttribute("message", "Register Successfully..");
            return "Signup";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Login";
    }

//    @Autowired
//    private UploadImageService uploadImage;
//    @PostMapping("/upload")
//    public ResponseEntity<Map> upload(@RequestParam("file") MultipartFile file){
//        Map data =uploadImage.upload(file);
//        //display_name
//        return new ResponseEntity<>(data, HttpStatus.OK);
//    }
}
