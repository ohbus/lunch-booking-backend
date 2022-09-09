package xyz.subho.lunchbooking.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "BookingsMealOptions")
@Table
@Data
@RequiredArgsConstructor
public class BookingsMealOptions implements Serializable {

	private static final long serialVersionUID = -5397108288093761149L;
	
	@EmbeddedId private BookingsMealOptionsId id;
	
	@ManyToOne(targetEntity = Bookings.class)
	@MapsId("bookingId")
	private Bookings bookings;
	
	@ManyToOne(targetEntity = MealOptions.class)
	@MapsId("mealOptionsId")
	private MealOptions mealOptions;
	
	@Basic
	  @CreatedDate
	  private Long createdAt;

	  @Basic
	  @CreatedBy
	  private String createdBy;

	  @Basic
	  @LastModifiedDate
	  private Long updatedAt;

	  @Basic
	  @LastModifiedBy
	  private String updatedBy;
	  
	  @Basic private Long version = 1L;
	  
	  @Basic private Long deletedAt;
	  
	  @Basic private String deletedBy;
	  
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

}
