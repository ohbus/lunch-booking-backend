package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(
    name = "meal_options",
    indexes = {@Index(columnList = "name", name = "name")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class MealOptions extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -6473537837965565355L;

  @Column(length = 30, nullable = false)
  private String name;

  @ManyToOne private Meals meals;

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
  }

  public void addBookings(List<Bookings> bookings) {
    if (Objects.nonNull(bookings))
      bookings.forEach(
          booking -> {
            var mapping = new BookingsMealOptions(booking, this);
            this.bookingsMealOptions.add(mapping);
            this.count++;
          });
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
}
