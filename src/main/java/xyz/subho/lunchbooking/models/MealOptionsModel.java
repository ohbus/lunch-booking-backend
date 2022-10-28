package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class MealOptionsModel implements Serializable {

  private Long id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  private Boolean selected;

  private int count;

  MealOptionsModel(String name) {
    this.name = name;
  }

  MealOptionsModel(String name, Integer count) {
    this.name = name;
    this.count = count;
  }
}
