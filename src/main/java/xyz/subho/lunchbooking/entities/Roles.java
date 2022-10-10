package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
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
  private Set<Permissions> permissions = new HashSet<>();
}
