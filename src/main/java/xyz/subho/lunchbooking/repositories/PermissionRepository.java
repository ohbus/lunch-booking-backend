package xyz.subho.lunchbooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.Permissions;

public interface PermissionRepository extends JpaRepository<Permissions, Long> {}
