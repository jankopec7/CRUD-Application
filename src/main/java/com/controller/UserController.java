package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<List<User>>(userService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOpt = userService.findById(id);

        if(userOpt.isPresent()){
            return new ResponseEntity<User>(userOpt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> add(@Validated @RequestBody User user){
        userService.add(user);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        Optional<User> optUser = userService.delete(id);

        if (optUser.isPresent()){
            return new ResponseEntity<User>(optUser.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?>update(@RequestBody User user){
        Optional<User> optUser = userService.update(user);

        if (optUser.isPresent()){
            return new ResponseEntity<User>(optUser.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


}

