package com.daadestroyer.LibraryManangementBackend.Controller;

import com.daadestroyer.LibraryManangementBackend.Entity.User;
import com.daadestroyer.LibraryManangementBackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    // add-user API
    @PostMapping("/add-user")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user) {
        this.userRepo.save(user);
        return "User Created";
    }

    // get-user API
    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        Optional<User> user = this.userRepo.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("User not found...", HttpStatus.NOT_FOUND);
        }
    }

    // get-all-user API
    @GetMapping("/get-all-user")
    public List<User> getAllUser() {
        List<User> user = this.userRepo.findAll();
        return user;
    }

    //delete-user-by-id API
    @DeleteMapping("/delete-user-by-id/{id}")
    public String deleteUserById(@PathVariable int id) {
        Optional<User> optionalUser = this.userRepo.findById(id);
        if (!optionalUser.isPresent()) {
            return "User not found...";
        } else {
            this.userRepo.deleteById(id);
            return "User " + id + " deleted successfully...";
        }
    }

    //delete-all-user API
    @DeleteMapping("/delete-all-user")
    public String deleteAllUsers() {
        List<User> list = this.userRepo.findAll();
        if(list.size()>0){
            this.userRepo.deleteAll();
            return "All user deleted successfully...";
        }
        else{
            return "No user exist...";
        }
    }

}
