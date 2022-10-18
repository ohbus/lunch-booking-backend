package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(
    name = "permissions",
    indexes = {@Index(columnList = "name", name = "name", unique = true)})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@NaturalIdCache
public class Permissions extends BaseEntity implements Serializable, GrantedAuthority {

  private static final long serialVersionUID = 3053930796866104874L;

  // USERS
  public static final long CREATE_USER = 1;
  public static final long READ_USER = 2;
  public static final long UPDATE_USER = 3;
  public static final long DELETE_USER = 4;

  // ROLES
  public static final long CREATE_ROLE = 5;
  public static final long READ_ROLE = 6;
  public static final long UPDATE_ROLE = 7;
  public static final long DELETE_ROLE = 8;

  // PERMISSIONS
  public static final long CREATE_PERMISSION = 9;
  public static final long READ_PERMISSION = 10;
  public static final long UPDATE_PERMISSION = 11;
  public static final long DELETE_PERMISSION = 12;

  // BOOKING
  public static final long CREATE_BOOKING = 13;
  public static final long READ_BOOKING = 14;
  public static final long UPDATE_BOOKING = 15;
  public static final long DELETE_BOOKING = 16;

  // MEAL
  public static final long CREATE_MEAL = 17;
  public static final long READ_MEAL = 18;
  public static final long UPDATE_MEAL = 19;
  public static final long DELETE_MEAL = 20;

  // MEAL_OPTIONS
  public static final long CREATE_MEAL_OPTIONS = 21;
  public static final long READ_MEAL_OPTIONS = 22;
  public static final long UPDATE_MEAL_OPTIONS = 23;
  public static final long DELETE_MEAL_OPTIONS = 24;

  public Permissions(Long id, String name) {
    this.setId(id);
    this.name = name;
  }

  @Column(name = "name", nullable = false, length = 64, unique = true)
  @NotNull
  @NaturalId
  private String name;

  @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
  @NotNull
  private boolean enabled = true;

  @Column(name = "note", length = 128)
  private String note;

  @Override
  public String getAuthority() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Permissions that = (Permissions) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public enum Permission {

    CREATE_USER("CREATE_USER"),
    READ_USER("READ_USER"),
    UPDATE_USER("UPDATE_USER"),
    DELETE_USER("DELETE_USER"),

    CREATE_ROLE("CREATE_ROLE"),
    READ_ROLE("READ_ROLE"),
    UPDATE_ROLE("UPDATE_ROLE"),
    DELETE_ROLE("DELETE_ROLE"),

    CREATE_PERMISSION("CREATE_PERMISSION"),
    READ_PERMISSION("READ_PERMISSION"),
    UPDATE_PERMISSION("UPDATE_PERMISSION"),
    DELETE_PERMISSION("DELETE_PERMISSION"),

    CREATE_BOOKING("CREATE_BOOKING"),
    READ_BOOKING("READ_BOOKING"),
    UPDATE_BOOKING("UPDATE_BOOKING"),
    DELETE_BOOKING("DELETE_BOOKING"),

    CREATE_MEAL("CREATE_MEAL"),
    READ_MEAL("READ_MEAL"),
    UPDATE_MEAL("UPDATE_MEAL"),
    DELETE_MEAL("DELETE_MEAL"),

    CREATE_MEAL_OPTIONS("CREATE_MEAL_OPTIONS"),
    READ_MEAL_OPTIONS("READ_MEAL_OPTIONS"),
    UPDATE_MEAL_OPTIONS("UPDATE_MEAL_OPTIONS"),
    DELETE_MEAL_OPTIONS("DELETE_MEAL_OPTIONS");

    private final String value;
    private static final Map<String, Permissions.Permission> CONSTANTS = new HashMap<>();

    static {
      for (Permissions.Permission c: values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private Permission(String value) {
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
    public static Permissions.Permission fromValue(String value) {
      Permissions.Permission constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }

  }
}
