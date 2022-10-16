package xyz.subho.lunchbooking.repositories;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.MealOptions;

public interface MealOptionsRepository extends JpaRepository<MealOptions, Long> {

  Set<MealOptions> findByBookingsMealOptions_Bookings_Id(@NonNull Long id);
}
