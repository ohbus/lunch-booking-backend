package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class MealsModel implements Serializable {

  private Long id;

  @NotBlank(message = "Name cannot be Blank")
  private String name;

  @NotNull(message = "Date Field cannot be null")
  private LocalDate date;

  private Long activatedAt;

  private Long lockedAt;

  @Size(min = 1, message = "Meal Options should contain greater than equals to 1 option")
  private Set<MealOptionsModel> mealOptions = new HashSet<>();

  public MealsModel(String name, LocalDate date, Set<MealOptionsModel> mealOptions) {
    this.name = name;
    this.date = date;
    this.mealOptions = mealOptions;
  }

  public MealsModel(String name, LocalDate date) {
    this.name = name;
    this.date = date;
  }
}
