package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(
    name = "roles",
    indexes = {@Index(name = "role", columnList = "role", unique = true)})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@NaturalIdCache
public class Roles extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1466436830227018993L;

  public static final long ADMINISTRATOR = 1;
  public static final long MANAGER = 2;
  public static final long EMPLOYEE = 3;
  public static final long CATERER = 4;

  public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";
  public static final String ROLE_MANAGER = "MANAGER";
  public static final String ROLE_EMPLOYEE = "EMPLOYEE";
  public static final String ROLE_CATERER = "CATERER";

  @Column(name = "role", nullable = false, length = 32)
  @NaturalId
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

  public enum Role {
    ADMINISTRATOR("ADMINISTRATOR"),
    MANAGER("MANAGER"),
    EMPLOYEE("EMPLOYEE"),
    CATERER("CATERER");
    private final String value;
    private static final Map<String, Role> CONSTANTS = new HashMap<>();

    static {
      for (Roles.Role c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private Role(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    @JsonValue
    public String value() {
      return this.value;
    }

    @JsonCreator
    public static Roles.Role fromValue(String value) {
      Roles.Role constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }
  }
}
