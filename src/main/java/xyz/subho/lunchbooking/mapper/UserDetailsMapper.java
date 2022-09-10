package xyz.subho.lunchbooking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.models.UserLoginResponseModel;

@Component("UserDetailsMapper")
public class UserDetailsMapper implements Mapper<UserMetadata, UserLoginResponseModel> {

  @Override
  public UserLoginResponseModel transform(UserMetadata source) {
    var model = new UserLoginResponseModel();
    BeanUtils.copyProperties(source, model);
    return model;
  }

  @Override
  public UserMetadata transformBack(UserLoginResponseModel source) {
    var model = new UserMetadata();
    BeanUtils.copyProperties(source, model);
    return model;
  }
}
