package xyz.subho.lunchbooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {}
