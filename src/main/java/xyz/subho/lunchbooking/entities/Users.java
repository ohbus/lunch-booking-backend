package xyz.subho.lunchbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(
    name = "users",
    indexes = {@Index(columnList = "username")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity {

  // username is the email ID
  @Column(name = "username", nullable = false, unique = true, updatable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "surname", nullable = false)
  private String surname;

  @Enumerated
  @Column(columnDefinition = "tinyint")
  private Meals mealPrefernce;

  @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
  private boolean enabled = true;

  @Basic private Long loginDt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Roles> roles = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Bookings> bookings = new ArrayList<>();

  @Column(name = "secured")
  private Boolean secured;
}
