package com.example.coffeeshop.repository;

import com.example.coffeeshop.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
