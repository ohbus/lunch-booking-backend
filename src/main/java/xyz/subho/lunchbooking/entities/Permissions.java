package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
}
