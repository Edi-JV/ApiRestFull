package com.m5b.ApiRestFull.app.controller;

import com.m5b.ApiRestFull.app.entity.User;
import com.m5b.ApiRestFull.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id){
        Optional<User> oUser=userService.findById(id);
        if(oUser.isPresent()){
            return ResponseEntity.ok(oUser);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id){
        Optional<User> oUser= userService.findById(id);
        if(oUser.isPresent()){
            oUser.get().setName(user.getName());
            oUser.get().setEmail(user.getEmail());
            oUser.get().setPassword(user.getPassword());
            oUser.get().setEstado(user.getEstado());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
        }else {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> oUser = userService.findById(id);
        if(oUser.isPresent()){
            userService.deleteById(id);
            return ResponseEntity.ok(oUser);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<User> showall(){
        List<User> userList= StreamSupport.stream(userService.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return userList;

    }

}
