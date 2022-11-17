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
