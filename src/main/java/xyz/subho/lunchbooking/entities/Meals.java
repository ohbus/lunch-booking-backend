package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(
    name = "meals",
    indexes = {
    		@Index(columnList = "name"),
    		@Index(columnList = "date")
    })
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class Meals extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -657646258883261176L;
	
	@Column(length = 30, nullable = false)
	private String name;
	
	private LocalDate date;
	
	private Long activatedAt;
	
	@OneToMany(mappedBy = "meals", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	  @JsonIgnore
	private List<MealOptions> mealOptions = new ArrayList<>();
	
	public boolean activate() {
		activatedAt = System.currentTimeMillis();
		return Objects.nonNull(activatedAt);
	}
	
	public boolean deactivate() {
		activatedAt = null;
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
  
}
