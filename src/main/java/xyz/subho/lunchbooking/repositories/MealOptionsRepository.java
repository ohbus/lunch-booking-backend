package xyz.subho.lunchbooking.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.MealOptions;

public interface MealOptionsRepository extends JpaRepository<MealOptions, Long> {

  Optional<MealOptions> findByBookingsMealOptions_Bookings_Id(@NonNull Long id);
}
