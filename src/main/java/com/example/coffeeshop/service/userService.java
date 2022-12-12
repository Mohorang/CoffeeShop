package com.example.coffeeshop.service;

import com.example.coffeeshop.model.Users;
import com.example.coffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class userService {
    private final UserRepository userRepository;

    @Transactional
    public void chargePoint(Long id,int point){
        Users user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );
        user.chargePoint(point);
    }
}
