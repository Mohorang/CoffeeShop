package com.example.coffeeshop.service;

import com.example.coffeeshop.domain.Users;
import com.example.coffeeshop.dto.requestDto.ChargePointDto;
import com.example.coffeeshop.dto.requestDto.SignUpDto;
import com.example.coffeeshop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Test
    public void 회원가입_테스트() throws Exception{
        //given
        SignUpDto dto = new SignUpDto();
        dto.setNickname("이준호");
        userService.signUp(dto);
        //when

        Users testUser = userRepository.findById(1L).orElseThrow();

        //then
        assertEquals("이준호",testUser.getNickname());
    }

    @Test
    public void 포인트_충전_테스트() throws Exception{
        //given
        SignUpDto dto = new SignUpDto();
        dto.setNickname("이준호");
        userService.signUp(dto);

        Users testUser = userRepository.findById(1L).orElseThrow();
        ChargePointDto chargePointDto = new ChargePointDto();
        chargePointDto.setUserId(testUser.getId());
        chargePointDto.setPoint(5000);

        //when
        userService.chargePoint(chargePointDto);

        //then
        assertEquals(5000,testUser.getPoint());
    }

}