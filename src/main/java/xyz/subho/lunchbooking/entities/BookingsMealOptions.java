package xyz.subho.lunchbooking.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "BookingsMealOptions")
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingsMealOptions implements Serializable {

  private static final long serialVersionUID = -5397108288093761149L;

  @EmbeddedId private BookingsMealOptionsId id;

  @ManyToOne(targetEntity = Bookings.class)
  @MapsId("bookingId")
  private Bookings bookings;

  @ManyToOne(targetEntity = MealOptions.class)
  @MapsId("mealOptionsId")
  private MealOptions mealOptions;

  @Basic @CreatedDate private Long createdAt;

  @Column(length = 100)
  @CreatedBy
  private String createdBy;

  @Basic @LastModifiedDate private Long updatedAt;

  @Column(length = 100)
  @LastModifiedBy
  private String updatedBy;

  @Basic private Long version = 1L;

  @Basic private Long deletedAt;

  @Column(length = 100)
  private String deletedBy;

  public BookingsMealOptions(Bookings bookings, MealOptions mealOptions) {
    this.id = new BookingsMealOptionsId(bookings.getId(), mealOptions.getId());
    this.bookings = bookings;
    this.mealOptions = mealOptions;
  }

  @PrePersist
  public void onCreate() {
    this.createdAt = System.currentTimeMillis();
    this.createdBy = getCurrentUser();
    this.version = 1L;
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = System.currentTimeMillis();
    this.updatedBy = getCurrentUser();
    this.version++;
  }

  private String getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (!(authentication instanceof AnonymousAuthenticationToken))
        ? authentication.getName()
        : null;
  }

  public void delete() {
    this.deletedAt = System.currentTimeMillis();
    this.deletedBy = getCurrentUser();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    BookingsMealOptions that = (BookingsMealOptions) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
