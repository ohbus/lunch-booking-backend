package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(
    name = "permissions",
    indexes = {@Index(columnList = "name")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class Permissions extends BaseEntity implements Serializable, GrantedAuthority {

  private static final long serialVersionUID = 3053930796866104874L;

  public Permissions(Long id, String name) {
    this.setId(id);
    this.name = name;
  }

  @Column(name = "name", nullable = false, length = 64)
  @NotNull
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
}
