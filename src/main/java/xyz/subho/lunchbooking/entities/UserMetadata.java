package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
    name = "users",
    indexes = {
    		@Index(columnList = "firstName"),
    		@Index(columnList = "lastName"),
    		@Index(columnList = "emailId")
    })
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class UserMetadata 
	extends BaseEntity 
		implements Serializable {
	
	private static final long serialVersionUID = -8209621126460711059L;

	private String firstName;
	
	private String lastName;
	
	private String emailId;

	  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	  @JsonIgnore
	  private List<Bookings> bookings = new ArrayList<>();
	  
}
