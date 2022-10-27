package xyz.subho.lunchbooking.services;

import java.util.Set;
import xyz.subho.lunchbooking.entities.UserMetadata;

public interface UserService {

  public UserMetadata getUserByEmail(String emailId);

  public UserMetadata getUserById(long id);

  public Set<UserMetadata> getUsersByFirstName(String firstName);

  public Set<UserMetadata> getUsersByLastName(String lastname);

  public UserMetadata getUserByMobile(String mobile);

  public UserMetadata saveUser(UserMetadata userMetadata);
}
