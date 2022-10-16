package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

@Entity
@Table(
    name = "users",
    indexes = {
      @Index(columnList = "firstName", name = "firstName"),
      @Index(columnList = "lastName", name = "lastName"),
      @Index(columnList = "emailId", name = "emailId"),
      @Index(columnList = "mobile", name = "mobile")
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
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
  @ToString.Exclude
  private Set<Bookings> bookings = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserMetadata that = (UserMetadata) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
