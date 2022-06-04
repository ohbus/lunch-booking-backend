package xyz.subho.lunchbooking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class Permissions extends BaseEntity {

  public Permissions(Long id, String permissionName) {
    this.setId(id);
    this.name = permissionName;
  }

  @Column(name = "name", nullable = false)
  private String name;

  // enabled as default
  @Column(name = "enabled")
  private boolean enabled = true;

  @Column(name = "note")
  private String note;
}
