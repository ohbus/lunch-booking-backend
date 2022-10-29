/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
