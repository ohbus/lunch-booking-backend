package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "available_bookings",
    indexes = {@Index(columnList = "date", name = "date")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AvailableBookings extends BaseEntity implements Serializable {

  @Column(nullable = false, updatable = false)
  LocalDate date;

  @ManyToOne
  @JoinColumn(name = "meal_options_id", updatable = false, nullable = false)
  MealOptions mealOptions;

  int count = 1;

  public AvailableBookings(LocalDate date, MealOptions mealOptions) {
    this.date = date;
    this.mealOptions = mealOptions;
  }

  public boolean isAvailable() {
    return count > 0;
  }

  public int claim() {
    if (isAvailable()) return --count;
    return -1;
  }

  public int add() {
    return ++count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    AvailableBookings that = (AvailableBookings) o;
    return count == that.count
        && Objects.equals(date, that.date)
        && Objects.equals(mealOptions, that.mealOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), date, mealOptions, count);
  }
}
