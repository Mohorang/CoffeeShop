package com.example.coffeeshop.service;

import com.example.coffeeshop.domain.PointRecord;
import com.example.coffeeshop.domain.Users;
import com.example.coffeeshop.dto.requestDto.ChargePointDto;
import com.example.coffeeshop.dto.requestDto.SignUpDto;
import com.example.coffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;


    public void chargePoint(ChargePointDto dto){
        Users user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );
        PointRecord pointRecord = new PointRecord();
        pointRecord.addPoint(user,dto.getPoint());
        user.chargePoint(dto.getPoint());
    }

    public void signUp(SignUpDto dto){
        Users user = new Users(dto.getNickname());
        userRepository.save(user);
    }
}
