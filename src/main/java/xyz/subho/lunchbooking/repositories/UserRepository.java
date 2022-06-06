package xyz.subho.lunchbooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {}
