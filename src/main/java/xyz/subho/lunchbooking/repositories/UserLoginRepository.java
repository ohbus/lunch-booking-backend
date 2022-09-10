package xyz.subho.lunchbooking.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

  public boolean existsUserLoginByUsername(String username);

  public Optional<UserLogin> findByUsername(String username);
}
