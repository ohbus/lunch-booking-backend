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

package xyz.subho.lunchbooking.config;

import jakarta.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.security.Permissions;
import xyz.subho.lunchbooking.entities.security.Roles;
import xyz.subho.lunchbooking.repositories.PermissionRepository;
import xyz.subho.lunchbooking.repositories.RolesRepository;

@Slf4j
@Component
@Transactional
public class SetupRolesLoader {

  @Autowired private PermissionRepository permissionRepository;

  @Autowired private RolesRepository rolesRepository;

  boolean alreadySetup = true;

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationEvent() {

    if (alreadySetup) return;

    while (permissionRepository.count() < Permissions.class.getFields().length)
      preparePermissions();

    while (rolesRepository.count() < Roles.class.getFields().length) prepareRoles();

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
