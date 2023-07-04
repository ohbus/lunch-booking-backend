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
import java.time.LocalDate;
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
    name = "meals",
    indexes = {
      @Index(columnList = "name", name = "name"),
      @Index(columnList = "date", name = "date")
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@NaturalIdCache
public class Meals extends BaseEntity implements Serializable {

  @Serial private static final long serialVersionUID = -657646258883261176L;

  @Column(length = 30, nullable = false)
  @NotNull
  private String name;

  @NaturalId
  @Column(unique = true, nullable = false)
  private LocalDate date;

  private Long activatedAt;

  private Long lockedAt;

  private Long readyAt;

  @OneToMany(mappedBy = "meals", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  @ToString.Exclude
  private Set<MealOptions> mealOptions = new HashSet<>();

  public long lock() {
    lockedAt = System.currentTimeMillis();
    return lockedAt;
  }

  public void unlock() {
    lockedAt = null;
  }

  public boolean isLocked() {
    return Objects.nonNull(lockedAt);
  }

  public void markReady() {
    readyAt = System.currentTimeMillis();
  }

  public void markUnReady() {
    readyAt = null;
  }

  public boolean isReady() {
    return Objects.nonNull(readyAt);
  }

  public long activate() {
    activatedAt = System.currentTimeMillis();
    return activatedAt;
  }

  public void deactivate() {
    activatedAt = null;
  }

  public boolean isActivated() {
    return Objects.nonNull(activatedAt);
  }

  public int addMealOptions(MealOptions mealOptions) {
    this.mealOptions.add(mealOptions);
    return this.mealOptions.size();
  }

  public int removeMealOptions(MealOptions mealOptions) {
    this.mealOptions.removeIf(option -> option.getId().equals(mealOptions.getId()));
    return this.mealOptions.size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Meals meals = (Meals) o;
    return getId() != null && Objects.equals(getId(), meals.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
