package xyz.subho.lunchbooking.config;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.Permissions;
import xyz.subho.lunchbooking.entities.Roles;
import xyz.subho.lunchbooking.repositories.PermissionRepository;
import xyz.subho.lunchbooking.repositories.RolesRepository;

@Slf4j
@Component
@Transactional
public class SetupRolesLoader implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired private PermissionRepository permissionRepository;

  @Autowired private RolesRepository rolesRepository;

  boolean alreadySetup = false;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    if (alreadySetup) return;

    preparePermissions();
    prepareRoles();

    alreadySetup = true;
  }

  private Set<Permissions> preparePermissions() {

    Set<Field> permissionsFields = new HashSet<>(Arrays.asList(Permissions.class.getFields()));
    Set<Permissions> permissionssMap = new HashSet<>();
    permissionsFields.forEach(
        field -> permissionssMap.add(createPermissionsIfNotExists(field.getName(), field)));
    return permissionssMap;
  }

  private Permissions createPermissionsIfNotExists(String name, Field relfectionField) {

    Optional<Permissions> permissionOptional = permissionRepository.findByName(name);

    if (permissionOptional.isPresent()) {
      return permissionOptional.get();
    }
    long id = 0L;
    try {
      id = Long.parseLong(relfectionField.get(Permissions.class.getField(name)).toString());
    } catch (IllegalArgumentException
        | IllegalAccessException
        | NoSuchFieldException
        | SecurityException e) {
      log.error("Cannot Reflect Permissions while loading Data");
      return null;
    }
    Permissions permissions = new Permissions(id, name);
    log.info("Creating Permission:{} with ID:{}", name, id);
    return permissionRepository.save(permissions);
  }

  private Set<Roles> prepareRoles() {

    Set<Field> rolesFields = new HashSet<>(Arrays.asList(Roles.class.getFields()));
    Set<Roles> rolesMap = new HashSet<>();
    rolesFields.forEach(field -> rolesMap.add(createRolessIfNotExists(field.getName(), field)));
    return rolesMap;
  }

  private Roles createRolessIfNotExists(String role, Field relfectionField) {

    Optional<Roles> rolesOptional = rolesRepository.findByRole(role);

    if (rolesOptional.isPresent()) {
      return rolesOptional.get();
    }
    long id = 0L;
    try {
      id = Long.parseLong(relfectionField.get(Roles.class.getField(role)).toString());
    } catch (IllegalArgumentException
        | IllegalAccessException
        | NoSuchFieldException
        | SecurityException e) {
      log.error("Cannot Reflect Permissions while loading Data");
      return null;
    }
    Roles roles = new Roles(id, role);
    log.info("Creating Roles:{} with ID:{}", role, id);
    return rolesRepository.save(roles);
  }
}
