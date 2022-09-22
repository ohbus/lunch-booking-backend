package xyz.subho.lunchbooking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.models.UserResponseModel;

@Component("UserDetailsMapper")
public class UserDetailsMapper implements Mapper<UserMetadata, UserResponseModel> {

  @Override
  public UserResponseModel transform(UserMetadata source) {
    var model = new UserResponseModel();
    BeanUtils.copyProperties(source, model);
    return model;
  }

  @Override
  public UserMetadata transformBack(UserResponseModel source) {
    var model = new UserMetadata();
    BeanUtils.copyProperties(source, model);
    return model;
  }
}
