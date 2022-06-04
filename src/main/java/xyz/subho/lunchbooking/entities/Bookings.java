package xyz.subho.lunchbooking.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
public class Bookings extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "users_id", updatable = false, nullable = false)
  private Users user;

  @Column private String mealPrefernce;

  @Column private LocalDate date;

  @Column(name = "claimed", columnDefinition = "boolean default false", nullable = false)
  private boolean claimed = false;
}
