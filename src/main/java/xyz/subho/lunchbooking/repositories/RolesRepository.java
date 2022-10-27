package xyz.subho.lunchbooking.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.lunchbooking.entities.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {

  public Optional<Roles> findByRole(String role);
}
