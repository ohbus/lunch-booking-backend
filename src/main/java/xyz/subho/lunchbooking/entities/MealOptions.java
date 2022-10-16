package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "meal_options",
    indexes = {@Index(columnList = "name", name = "name")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class MealOptions extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -6473537837965565355L;

  @Column(length = 30, nullable = false)
  private String name;

  @ManyToOne(targetEntity = Meals.class)
  private Meals meals;

  private Integer count = 0;

  @OneToMany(
      mappedBy = "mealOptions",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  @JsonIgnore
  private Set<BookingsMealOptions> bookingsMealOptions = new HashSet<>();

  public MealOptions(String name) {
    this.name = name;
    count = 0;
  }

  public void addBookings(List<Bookings> bookings) {
    if (Objects.nonNull(bookings))
      bookings.forEach(
          booking -> {
            var mapping = new BookingsMealOptions(booking, this);
            this.bookingsMealOptions.add(mapping);
            incrementCount();
          });
  }

  private void incrementCount() {
    if (Objects.isNull(count)) count = 0;
    count++;
  }

  public void addBooking(Bookings booking) {
    if (Objects.nonNull(booking)) addBookings(Arrays.asList(booking));
  }

  public void removeBooking(Bookings booking) {
    if (Objects.nonNull(booking) && Objects.nonNull(booking.getId()))
      removeBookingById(booking.getId());
  }

  public void removeBookingById(long bookingId) {
    Set<BookingsMealOptions> toBeDeleted =
        bookingsMealOptions.stream()
            .filter(mapping -> mapping.getId().getBookingId().equals(bookingId))
            .collect(Collectors.toSet());
    bookingsMealOptions.removeAll(toBeDeleted);
    count -= toBeDeleted.size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MealOptions)) return false;
    return this.getId() != null && this.getId().equals(((MealOptions) o).getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
