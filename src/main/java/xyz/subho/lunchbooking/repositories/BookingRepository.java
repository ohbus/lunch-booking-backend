package xyz.subho.lunchbooking.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {

  boolean existsByDateAndUser_IdAndCancelledAtNull(@NonNull LocalDate date, @NonNull Long id);

  Set<Bookings> findByUser_IdAndDate(@NonNull Long id, @NonNull LocalDate date);

  Optional<Bookings> findByDateAndUser_IdAndCancelledAtNull(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDateLessThanAndUser_IdOrderByDateDesc(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDateGreaterThanAndUser_IdOrderByDateAsc(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDateGreaterThanEqualAndUser_IdOrderByDateAsc(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDate(@NonNull LocalDate date);
}
