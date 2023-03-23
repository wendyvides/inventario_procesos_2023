package com.procesos.inventario.controllers;

import com.procesos.inventario.models.User;
import com.procesos.inventario.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        Map response = new HashMap();
        try{
            return new ResponseEntity(userServiceImp.getUser(id), HttpStatus.OK);
        }catch (Exception e){
            response.put("status","404");
            response.put("message", "user not found!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/users")
    public ResponseEntity findUsers(){
        Map response = new HashMap();
        try{
            return new ResponseEntity(userServiceImp.allUsers(), HttpStatus.OK);
        }catch (Exception e){
            response.put("status","404");
            response.put("message", "user not found!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping(value = "/user")
    public ResponseEntity saveUser(@RequestBody User user){
        Map response = new HashMap();
        Boolean userResp = userServiceImp.createUser(user);
        if(userResp){
            response.put("status","201");
            response.put("message", "user created successfully");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        response.put("status", "400");
        response.put("message", "error creating user");
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity updateUser(Long id, @RequestBody User user){
        Map response = new HashMap();
        Boolean userResp = userServiceImp.updateUser(id, user);
        if(userResp){
            response.put("status","200");
            response.put("message", "user updated successfully");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        response.put("status", "400");
        response.put("message", "error updating user");
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
