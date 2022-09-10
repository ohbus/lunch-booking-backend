package xyz.subho.lunchbooking.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.UserMetadata;

public interface UserMetadataRepository extends JpaRepository<UserMetadata, Long> {

  public Optional<UserMetadata> findByFirstName(String firstName);

  public Optional<UserMetadata> findByLastName(String lastName);

  public Optional<UserMetadata> findByEmailId(String emailId);

  public Optional<UserMetadata> findByMobile(String mobile);
}
