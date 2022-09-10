package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class Bookings extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -9138314713309636521L;

  @ManyToOne
  @JoinColumn(name = "users_id", updatable = false, nullable = false)
  @NotNull
  private UserMetadata user;

  private LocalDate date;

  private Long claimedAt;

  private Long cancelledAt;

  @OneToMany(
      mappedBy = "bookings",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  @JsonIgnore
  private List<BookingsMealOptions> bookingsMealOptions = new ArrayList<>();
}
