package xyz.subho.lunchbooking.services.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.models.Email;
import xyz.subho.lunchbooking.services.MailService;

@Slf4j
@Async
@Service
public class MailServiceImpl implements MailService {

  @Autowired private JavaMailSender mailSender;

  @Value("${custom.mail.from.address:hello@subho.xyz}")
  private String mailFromAddress;

  @Value("${custom.mail.from.name:Subhrodip Mohanta}")
  private String mailFromName;

  @Value("${custom.mail.reply-to.address:no-reply+lunch-booking@subho.xyz}")
  private String replyToAddress;

  @Value("${custom.mail.reply-to.name:Subhrodip Mohanta}")
  private String replyToName;

  @Override
  public void sendMail(@NonNull Email email) {

    MimeMessage message = mailSender.createMimeMessage();

    try {
      MimeMessageHelper helper =
          new MimeMessageHelper(
              message,
              MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());

      helper.setFrom(mailFromAddress, mailFromName);
      helper.setTo(email.getRecipients().toArray(String[]::new));
      helper.setReplyTo(replyToAddress, replyToName);

      helper.setCc(email.getCcList().toArray(String[]::new));
      helper.setBcc(email.getBccList().toArray(String[]::new));

      helper.setSubject(email.getSubject());
      helper.setText(email.getBody());

      email
          .getAttachments()
          .forEach(
              (filename, dataSource) -> {
                try {
                  helper.addAttachment(filename, dataSource);
                } catch (MessagingException e) {
                  log.error("Could not attach file:{} for Email", filename);
                }
              });

      mailSender.send(message);
      log.debug("Mail Sent to:{}", email.getRecipients().toString());
    } catch (MessagingException | UnsupportedEncodingException e) {
      log.error("Could not send email due to {}", e.getMessage());
    }
  }
}
