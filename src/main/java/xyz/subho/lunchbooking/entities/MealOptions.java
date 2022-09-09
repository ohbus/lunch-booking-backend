package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    name = "meal_options",
    indexes = {@Index(columnList = "name")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class MealOptions extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6473537837965565355L;
	
	@Column(length = 30, nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "meals_id", nullable = false)
	private Meals meals;
	
	private Integer count = 0;
	
	@OneToMany(
		      mappedBy = "mealOptions",
		      cascade = CascadeType.ALL,
		      fetch = FetchType.LAZY,
		      orphanRemoval = true)
		  @JsonIgnore
	  private List<BookingsMealOptions> bookingsMealOptions = new ArrayList<>();
	
	public void addBookings(List<Bookings> bookings) {
		bookings.forEach(booking -> {
			var mapping = new BookingsMealOptions(booking, this);
			this.bookingsMealOptions.add(mapping);
			this.count++;
		});
	}
	
	public void removeBooking(Bookings booking) {
		List<BookingsMealOptions> toBeDeleted = 
				bookingsMealOptions.stream()
					.filter(mapping -> mapping.getId().getBookingId().equals(booking.getId()))
					.collect(Collectors.toList());
		bookingsMealOptions.removeAll(toBeDeleted);
		count -= toBeDeleted.size();
	}

}
