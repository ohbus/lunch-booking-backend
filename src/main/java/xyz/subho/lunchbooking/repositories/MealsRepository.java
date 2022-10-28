package xyz.subho.lunchbooking.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.Meals;

public interface MealsRepository extends JpaRepository<Meals, Long> {

  List<Meals> findByNameLikeIgnoreCaseOrderByDateDesc(@NonNull String name);

  List<Meals> findByDateGreaterThanEqualOrderByDateAsc(@NonNull LocalDate date);

  List<Meals> findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
      @NonNull LocalDate date);

  List<Meals> findByDateLessThanOrderByDateDesc(@NonNull LocalDate date);

  boolean existsByDate(@NonNull LocalDate date);
}
