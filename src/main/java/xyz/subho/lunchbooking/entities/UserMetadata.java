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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(
    name = "users",
    indexes = {
      @Index(columnList = "firstName", name = "firstName"),
      @Index(columnList = "lastName", name = "lastName"),
      @Index(columnList = "emailId", name = "emailId"),
      @Index(columnList = "mobile", name = "mobile")
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@NaturalIdCache
public class UserMetadata extends BaseEntity implements Serializable {

  @Serial private static final long serialVersionUID = -8209621126460711059L;

  @Column(length = 50, nullable = false)
  @NotNull
  private String firstName;

  @Column(length = 50, nullable = false)
  @NotNull
  private String lastName;

  @Column(length = 100, nullable = false, unique = true)
  @NotNull
  @NaturalId
  private String emailId;

  @Column(length = 15, unique = true)
  private String mobile;

  private Long subscribedAt;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  @ToString.Exclude
  private Set<Bookings> bookings = new HashSet<>();

  public boolean isSubscribed() {
    return Objects.nonNull(subscribedAt);
  }

  public void subscribe() {
    subscribedAt = System.currentTimeMillis();
  }

  public void unsubscribe() {
    subscribedAt = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserMetadata that = (UserMetadata) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
