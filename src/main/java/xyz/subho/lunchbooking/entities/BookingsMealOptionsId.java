package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Embeddable
@Data
@AllArgsConstructor
@With
public class BookingsMealOptionsId implements Serializable {

  private static final long serialVersionUID = -1180526388055863804L;

  private Long bookingId;

  private Long mealOptionsId;
}
