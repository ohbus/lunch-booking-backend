package xyz.subho.lunchbooking.services;

import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.models.Email;

public interface MailService {

  public void sendMail(@NonNull Email email);
}
