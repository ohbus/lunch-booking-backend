package xyz.subho.lunchbooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.MealOptions;

public interface MealOptionsRepository extends JpaRepository<MealOptions, Long> {}
