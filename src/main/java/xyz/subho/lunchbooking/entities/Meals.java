package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
    name = "meals",
    indexes = {
      @Index(columnList = "name", name = "name"),
      @Index(columnList = "date", name = "date")
    })
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@With
public class Meals extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -657646258883261176L;

  @Column(length = 30, nullable = false)
  @NotNull
  private String name;

  private LocalDate date;

  private Long activatedAt;

  @OneToMany(
      mappedBy = "meals",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  @JsonIgnore
  @ToString.Exclude
  private Set<MealOptions> mealOptions = new HashSet<>();

  public boolean isActivated() {
    return Objects.nonNull(activatedAt);
  }

  public boolean activate() {
    activatedAt = System.currentTimeMillis();
    return true;
  }

  public boolean deactivate() {
    activatedAt = null;
    return false;
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
