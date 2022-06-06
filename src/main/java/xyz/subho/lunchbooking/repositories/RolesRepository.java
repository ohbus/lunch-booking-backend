package xyz.subho.lunchbooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {}
