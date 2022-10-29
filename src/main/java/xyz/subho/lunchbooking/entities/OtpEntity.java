/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpEntity implements Serializable {

  @Id @GeneratedValue Long id;

  @Column(updatable = false, nullable = false)
  Integer otp;

  @Column(updatable = false, nullable = false)
  Long userId;

  LocalDateTime sentAt;

  @Column(nullable = false, updatable = false)
  LocalDateTime issuesAt = LocalDateTime.now();

  LocalDateTime verifiedAt;

  @Column(nullable = false, updatable = false)
  LocalDateTime expiresAt;

  public OtpEntity(@NonNull Long userId) {
    this.userId = userId;
  }

  public boolean isSent() {
    return Objects.nonNull(sentAt);
  }

  public boolean isIssued() {
    return Objects.nonNull(issuesAt);
  }

  public boolean isVerified() {
    return Objects.nonNull(verifiedAt);
  }

  public boolean isExpired() {
    return LocalDateTime.now().isBefore(expiresAt);
  }

  public void send() {
    sentAt = LocalDateTime.now();
  }

  public void issue() {
    issuesAt = LocalDateTime.now();
  }

  public void verify() {
    verifiedAt = LocalDateTime.now();
  }
}
