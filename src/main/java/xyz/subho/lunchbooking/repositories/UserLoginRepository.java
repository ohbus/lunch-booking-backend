package xyz.subho.lunchbooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.subho.lunchbooking.entities.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {}
