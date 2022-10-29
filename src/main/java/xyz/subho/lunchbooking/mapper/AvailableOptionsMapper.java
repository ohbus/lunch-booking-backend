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
import xyz.subho.lunchbooking.entities.AvailableBookings;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.models.AvailableOptionsResponseModel;

@Component("AvailableOptionsMapper")
public class AvailableOptionsMapper
    implements Mapper<AvailableBookings, AvailableOptionsResponseModel> {

  @Override
  public AvailableOptionsResponseModel transform(AvailableBookings source) {
    return new AvailableOptionsResponseModel(
        source.getId(),
        source.getDate(),
        source.getMealOptions().getId(),
        source.getMealOptions().getName(),
        source.getCount());
  }

  @Override
  public AvailableBookings transformBack(AvailableOptionsResponseModel source) {
    var mealOption = new MealOptions().withName(source.mealOptionName());
    mealOption.setId(source.mealOptionId());
    var entity = new AvailableBookings(source.date(), mealOption);
    BeanUtils.copyProperties(source, entity);
    return entity;
  }
}
