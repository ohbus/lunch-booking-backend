package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(
    name = "bookings",
    indexes = {
      @Index(name = "date", columnList = "date"),
      @Index(name = "users_id", columnList = "users_id")
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class Bookings extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -9138314713309636521L;

  @ManyToOne
  @JoinColumn(name = "users_id", updatable = false, nullable = false)
  @NotNull
  private UserMetadata user;

  private LocalDate date;

  private Long claimedAt;

  private Long cancelledAt;

  @OneToMany(mappedBy = "bookings", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<BookingsMealOptions> bookingsMealOptions = new HashSet<>();

  public long availBooking() {
    claimedAt = System.currentTimeMillis();
    return claimedAt;
  }

  public long cancelBooking() {
    cancelledAt = System.currentTimeMillis();
    return cancelledAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Bookings bookings = (Bookings) o;
    return user.equals(bookings.user)
        && date.equals(bookings.date)
        && Objects.equals(claimedAt, bookings.claimedAt)
        && Objects.equals(cancelledAt, bookings.cancelledAt)
        && bookingsMealOptions.equals(bookings.bookingsMealOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), user, date, claimedAt, cancelledAt, bookingsMealOptions);
  }
}
