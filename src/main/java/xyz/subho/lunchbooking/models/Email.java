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

package xyz.subho.lunchbooking.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.activation.DataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Email {

  private Set<String> recipients = new HashSet<>(4);
  private Set<String> ccList = new HashSet<>(4);
  private Set<String> bccList = new HashSet<>(4);
  private String subject;
  private String body;
  private boolean isHtml = false;
  private Map<String, DataSource> attachments = new HashMap<>(4);

  public Email(String to, String subject, String body) {
    addRecipient(to);
    this.subject = subject;
    this.body = body;
  }

  public Email(String to, String subject, String body, String filename, DataSource dataSource) {
    this(to, subject, body);
    addAttachment(filename, dataSource);
  }

  public Email addRecipient(String recipient) {
    recipients.add(recipient);
    return this;
  }

  public Email addCc(String recipient) {
    ccList.add(recipient);
    return this;
  }

  public Email addBcc(String recipient) {
    bccList.add(recipient);
    return this;
  }

  public Email addAttachment(String filename, DataSource attachment) {
    attachments.put(filename, attachment);
    return this;
  }
}
