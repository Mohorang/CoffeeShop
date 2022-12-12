package com.example.coffeeshop.controller;

import com.example.coffeeshop.model.Users;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class userController {


    private final userService service;
    private final UserRepository userRepository;

    @PostMapping
    public String addUser(){
        Users user = new Users();
        userRepository.save(user);
        return "ok";
    }

    @PostMapping("{userId}")
    public String chargePoint(@PathVariable Long userId, @RequestParam int point){
        service.chargePoint(userId,point);
        return "포인트 충전 완료";
    }
}
