package xyz.subho.lunchbooking.repositories;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {

  boolean existsByUser_IdAndDate(@NonNull Long id, @NonNull LocalDate date);

  Optional<Bookings> findByUser_IdAndDate(@NonNull Long id, @NonNull LocalDate date);

  Optional<Bookings> findByDateAndUser_IdAndCancelledAtNull(
      @NonNull LocalDate date, @NonNull Long id);
}
