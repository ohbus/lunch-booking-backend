package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(
    name = "users",
    indexes = {
      @Index(columnList = "firstName", name = "firstName"),
      @Index(columnList = "lastName", name = "lastName"),
      @Index(columnList = "emailId", name = "emailId"),
      @Index(columnList = "mobile", name = "mobile")
    })
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class UserMetadata extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -8209621126460711059L;

  @Column(length = 50, nullable = false)
  @NotNull
  private String firstName;

  @Column(length = 50, nullable = false)
  @NotNull
  private String lastName;

  @Column(length = 100, nullable = false, unique = true)
  @NotNull
  private String emailId;

  @Column(length = 15, unique = true)
  private String mobile;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Bookings> bookings = new HashSet<>();
}
