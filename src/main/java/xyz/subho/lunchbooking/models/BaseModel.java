package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class BaseModel implements Serializable {

  private static final long serialVersionUID = 4375949716879890403L;

  private Long id;
  private Long createdAt;
  private String createdBy;
  private Long updatedAt;
  private String updatedBy;
  private Long version;
  private Long deletedAt;
  private String deletedBy;
}
