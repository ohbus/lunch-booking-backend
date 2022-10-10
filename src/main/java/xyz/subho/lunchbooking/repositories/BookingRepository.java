package xyz.subho.lunchbooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {}
