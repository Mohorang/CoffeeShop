package com.example.coffeeshop.controller;

import com.example.coffeeshop.domain.Users;
import com.example.coffeeshop.dto.requestDto.ChargePointDto;
import com.example.coffeeshop.dto.requestDto.SignUpDto;
import com.example.coffeeshop.dto.responseDto.ChargePointResponseDto;
import com.example.coffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    @PostMapping("/signup")
    public String addUser(@RequestBody SignUpDto dto){
        userService.signUp(dto);
        return "ok";
    }

    @PostMapping("/charge-point")
    public String chargePoint(@RequestBody ChargePointDto dto){
        userService.chargePoint(dto);
        return "포인트 충전 완료";
    }
}
