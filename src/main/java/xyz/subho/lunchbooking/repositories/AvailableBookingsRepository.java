package xyz.subho.lunchbooking.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.AvailableBookings;

public interface AvailableBookingsRepository extends JpaRepository<AvailableBookings, Long> {

  List<AvailableBookings> findByDateOrderByCountDesc(@NonNull LocalDate date);

  List<AvailableBookings> findByMealOptions_IdOrderByCountDesc(@NonNull Long id);

  Optional<AvailableBookings> findByDateAndMealOptions_Id(
      @NonNull LocalDate date, @NonNull Long id);
}
