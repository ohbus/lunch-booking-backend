package xyz.subho.lunchbooking.repositories;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.UserMetadata;

public interface UserMetadataRepository extends JpaRepository<UserMetadata, Long> {

  Optional<UserMetadata> findByEmailId(@NonNull String emailId);

  Optional<UserMetadata> findByMobile(@NonNull String mobile);

  Set<UserMetadata> findByFirstNameContainsIgnoreCase(@NonNull String firstName);

  Set<UserMetadata> findByLastNameContainsIgnoreCase(@NonNull String lastName);
}
