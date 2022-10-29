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
