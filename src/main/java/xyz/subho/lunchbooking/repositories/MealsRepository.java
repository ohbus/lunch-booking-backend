package xyz.subho.lunchbooking.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.Meals;

public interface MealsRepository extends JpaRepository<Meals, Long> {

  List<Meals> findByNameLikeIgnoreCaseOrderByDateDesc(@NonNull String name);

  Optional<Meals> findByDateAndMealOptions_BookingsMealOptions_Bookings(
      @NonNull LocalDate date, @NonNull Bookings bookings);

  List<Meals> findByDateGreaterThanEqualOrderByDateAsc(@NonNull LocalDate date);

  List<Meals> findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
      @NonNull LocalDate date);

  List<Meals> findByDateLessThanOrderByDateDesc(@NonNull LocalDate date);

  boolean existsByDate(@NonNull LocalDate date);
}
