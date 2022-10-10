package xyz.subho.lunchbooking.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import xyz.subho.lunchbooking.entities.Permissions;

public class Testtt {

  public static void main(String[] args)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {

    System.out.println(
        Permissions.class
            .getField("UPDATE_USER")
            .get(Permissions.class.getField("UPDATE_USER"))
            .toString());

    List<Field> fields = new ArrayList<>(Arrays.asList(Permissions.class.getFields()));
    fields.forEach(
        field -> {
          String name = field.getName();
          try {
            System.out.println(name + ":" + field.get(name));
          } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        });
  }
}
