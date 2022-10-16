package xyz.subho.lunchbooking.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class Roles extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1466436830227018993L;

  public static final long ADMINISTRATOR = 1;
  public static final long MANAGER = 2;
  public static final long EMPLOYEE = 3;
  public static final long CATERER = 4;

  @Column(name = "role", nullable = false, length = 32)
  private String role;

  public Roles(Long id, String role) {
    this.setId(id);
    this.role = role;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "permissions_roles",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id"))
  @ToString.Exclude
  private Set<Permissions> permissions = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Roles roles = (Roles) o;
    return getId() != null && Objects.equals(getId(), roles.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
