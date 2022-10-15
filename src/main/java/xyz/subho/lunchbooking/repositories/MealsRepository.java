package xyz.subho.lunchbooking.repositories;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.Meals;

public interface MealsRepository extends JpaRepository<Meals, Long> {

  Set<Meals> findByNameLikeIgnoreCaseOrderByDateDesc(@NonNull String name);

  Set<Meals> findByDateGreaterThanEqualOrderByDateDesc(@NonNull LocalDate date);

  Set<Meals> findByDateLessThanOrderByDateDesc(@NonNull LocalDate date);
}
