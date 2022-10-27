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

  private static final long serialVersionUID = -657646258883261176L;

  @Column(length = 30, nullable = false)
  @NotNull
  private String name;

  @NaturalId
  @Column(unique = true, nullable = false)
  private LocalDate date;

  private Long activatedAt;

  private Long lockedAt;

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
