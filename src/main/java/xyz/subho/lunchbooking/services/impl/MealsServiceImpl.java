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

package xyz.subho.lunchbooking.services.impl;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import javax.mail.util.ByteArrayDataSource;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.AvailableBookings;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.exceptions.*;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.*;
import xyz.subho.lunchbooking.repositories.AvailableBookingsRepository;
import xyz.subho.lunchbooking.repositories.BookingRepository;
import xyz.subho.lunchbooking.repositories.MealOptionsRepository;
import xyz.subho.lunchbooking.repositories.MealsRepository;
import xyz.subho.lunchbooking.services.MailService;
import xyz.subho.lunchbooking.services.MealsService;
import xyz.subho.lunchbooking.util.QRUtil;

@Slf4j
@Service
public class MealsServiceImpl implements MealsService {

  @Autowired private MailService mailService;

  @Autowired private MealsRepository mealsRepository;

  @Autowired private MealOptionsRepository mealOptionsRepository;

  @Autowired private BookingRepository bookingRepository;

  @Autowired private AvailableBookingsRepository availableBookingsRepository;

  @Autowired
  @Qualifier("MealDetailsMapper")
  private Mapper<Meals, MealsModel> mealsRequestModelMapper;

  @Autowired
  @Qualifier("BookingResponseMapper")
  private Mapper<Bookings, BookingResponseModel> bookingResponseModelMapper;

  @Autowired
  @Qualifier("AvailableOptionsMapper")
  private Mapper<AvailableBookings, AvailableOptionsResponseModel>
      bookingsAvailableOptionsResponseModelMapper;

  @Value("${custom.mail.manager.to.addresses:hello@subho.xyz}")
  private String managerToAddresses;

  @Value("${custom.mail.manager.cc.addresses}")
  private String managerCcAddresses;

  @Override
  @Transactional
  public MealsModel createMeal(@NonNull MealsModel mealsModel) {
    log.debug("Creating Meals:{}", mealsModel);
    if (mealsRepository.existsByDate(mealsModel.getDate())) {
      log.error("Meal already exists for the date:{}", mealsModel.getDate());
      throw new SelectionNotAvailableException(
          String.format(
              "Meal:%s cannot be saved as there already exists a meal for date:%s",
              mealsModel.getName(), mealsModel.getDate()));
    }
    var meal = mealsRequestModelMapper.transformBack(mealsModel);
    var mealModel = mealsRequestModelMapper.transform(mealsRepository.save(meal));
    addMealToMealOptions(meal.getMealOptions(), meal);
    return mealModel;
  }

  private void addMealToMealOptions(
      @NonNull Set<MealOptions> mealOptionsSet, @NonNull Meals meals) {
    mealOptionsSet.forEach(mealOptions -> mealOptions.setMeals(meals));
  }

  @Override
  public Meals getMealById(long id) {

    log.debug("Finding Meal with ID:{}", id);
    var mealOpt = mealsRepository.findById(id);
    if (mealOpt.isEmpty()) {
      log.error("Cannot Find Meal with ID:{}", id);
      throw new MealNotFoundException(String.format("There is not Meal with ID:%s", id));
    }
    log.debug("Found Meal with ID:{}", id);
    return mealOpt.get();
  }

  @Override
  public MealOptions getMealOptionById(long id) {

    log.debug("Finding Meal Option with ID:{}", id);
    var mealOptionOpt = mealOptionsRepository.findById(id);
    if (mealOptionOpt.isEmpty()) {
      log.error("Cannot Find Meal Options with ID:{}", id);
      throw new MealOptionsNotFoundException(
          String.format("Meal Options with ID:%s is not present", id));
    }
    log.debug("Found Meal Options with ID:{}", id);
    return mealOptionOpt.get();
  }

  @Transactional
  public Meals updateMeal(@NonNull Meals meals) {
    if (Objects.isNull(meals.getId())) {
      log.error("Meal ID cannot be none while updating");
      throw new MealNotFoundException("Meal ID cannot be blank");
    }
    log.debug("Updating Meal with ID:{}", meals.getId());
    return mealsRepository.save(meals);
  }

  @Transactional
  public MealOptions updateMealOptions(@NonNull MealOptions mealOptions) {
    if (Objects.isNull(mealOptions.getId())) {
      log.error("Meal Options ID cannot be blank while updating");
      throw new MealOptionsNotFoundException("Meal Options ID cannot be blank");
    }
    log.debug("Updating Meal Options with ID:{}", mealOptions.getId());
    return mealOptionsRepository.save(mealOptions);
  }

  @Override
  public MealOptions getMealOptionsByBookingId(long id) {
    var mealOptionOpt = mealOptionsRepository.findByBookings_Id(id);
    if (mealOptionOpt.isEmpty()) {
      log.error("Cannot Find Meal Options with Booking ID:{}", id);
      throw new MealOptionsNotFoundException(
          String.format("Meal Options with Booking ID:%s is not present", id));
    }
    log.debug("Found Meal Options with Booking ID:{}", id);
    return mealOptionOpt.get();
  }

  @Override
  @Transactional
  public MealsModel lockMeal(long mealId) {
    log.debug("Trying to Lock Meal ID:{}", mealId);
    var meal = getMealById(mealId);
    if (meal.isLocked()) {
      log.error("Meal:{} is already Locked!", mealId);
      throw new InvalidMealOperation(String.format("Meal:%s is already Locked!", meal.getName()));
    }
    if (!meal.isActivated()) {
      log.error("Meal:{} is being tried to lock before being activated!", mealId);
      throw new InvalidMealOperation(String.format("Meal:%s is not Activated!", meal.getName()));
    }
    meal.lock();
    log.debug("Meal ID:{} is being Locked", mealId);
    var mealModel = mealsRequestModelMapper.transform(meal);
    sendCountEmails(mealModel);
    return mealModel;
  }

  @Override
  @Transactional
  public MealsModel unlockMeal(long mealId) {
    var meal = getMealById(mealId);
    if (!meal.isLocked()) {
      log.error("Meal:{} is not Locked!", mealId);
      throw new InvalidMealOperation(String.format("Meal:%s is not Locked!", meal.getName()));
    }
    meal.unlock();
    return mealsRequestModelMapper.transform(meal);
  }

  @Override
  @Transactional
  public MealsModel activateMeal(long mealId) {
    log.debug("Trying to Activate Meal ID:{}", mealId);
    var meal = getMealById(mealId);
    if (meal.isActivated()) {
      log.error("Meal:{} is already Activated!", mealId);
      throw new InvalidMealOperation(
          String.format("Meal:%s is already Activated!", meal.getName()));
    }
    meal.activate();
    log.debug("Meal ID:{} is being Activated", mealId);
    return mealsRequestModelMapper.transform(meal);
  }

  @Override
  @Transactional
  public MealsModel deactivateMeal(long mealId) {
    log.debug("Trying to deactivate Meal ID:{}", mealId);
    var meal = getMealById(mealId);
    if (!meal.isActivated()) {
      log.error("Meal:{} is already Deactivated!", mealId);
      throw new InvalidMealOperation(
          String.format("Meal:%s is already Deactivated!", meal.getName()));
    }
    meal.deactivate();
    log.debug("Meal ID:{} is being deactivated", mealId);
    return mealsRequestModelMapper.transform(meal);
  }

  @Override
  @Transactional
  public MealsModel makeMealReady(long mealId) {
    log.debug("Marking Meal ID:{} as Ready", mealId);
    var meal = getMealById(mealId);
    if (meal.isReady()) {
      log.error("Meal:{} is already Ready!", mealId);
      throw new InvalidMealOperation(String.format("Meal:%s is already Ready!", meal.getName()));
    }
    if (!meal.isLocked()) {
      log.error("Meal:{} is being tried to make READY before being locked!", mealId);
      throw new InvalidMealOperation(String.format("Meal:%s is not Locked!", meal.getName()));
    }
    if (!LocalDate.now().isEqual(meal.getDate())) {
      log.error(
          "Meal ID:{} Date for:{} cannot be marked Ready today!", meal.getId(), meal.getDate());
      throw new InvalidMealOperation(
          String.format(
              "Cannot Ready Meal:%s today. Can be marked Ready only on:%s",
              meal.getName(), meal.getDate()));
    }
    meal.markReady();
    log.debug("Sending Email Information to People Booking");
    sendReadyEmails(meal);
    log.debug("Meal ID:{} is now Ready", mealId);
    return mealsRequestModelMapper.transform(meal);
  }

  @Async
  protected void sendCountEmails(MealsModel meal) {
    var email = new Email();
    var totalCount =
        meal.getMealOptions().stream()
            .map(MealOptionsModel::getCount)
            .mapToInt(Integer::intValue)
            .sum();

    var bodyPrelude =
        String.format("Hi,%n%nMeal Details: %s%nOptions with Count as follows:%n", meal.getName());
    var bodyInterlude =
        meal.getMealOptions().stream()
            .map(
                mealOptionsModel ->
                    Pair.of(mealOptionsModel.getName(), mealOptionsModel.getCount()))
            .map(
                mealOption ->
                    String.format("%s :: %s", mealOption.getFirst(), mealOption.getSecond()))
            .collect(Collectors.joining("\n"));
    var bodyPostlude = String.format("%n%nTotal Count :: %s%n%nCheers.", totalCount);

    new ArrayList<>(Arrays.asList(managerToAddresses.split(","))).forEach(email::addRecipient);
    new ArrayList<>(Arrays.asList(managerCcAddresses.split(","))).forEach(email::addCc);
    email.setSubject(String.format("Lunch Booking for %s", meal.getDate()));
    email.setBody(String.format("%s%s%s", bodyPrelude, bodyInterlude, bodyPostlude));

    mailService.sendMail(email);
  }

  @Async
  protected void sendReadyEmails(Meals meal) {

    var bookingList = bookingRepository.findByDateAndCancelledAtNull(meal.getDate());
    log.debug("Sending QR Booking Information to {} users", bookingList.size());
    bookingList.forEach(
        booking -> {
          var user = booking.getUser();
          var bookingModel = bookingResponseModelMapper.transform(booking);
          var bookingId = Long.toString(booking.getId());

          log.debug("Sending QR Booking Information to:{}", user.getEmailId());
          mailService.sendMail(
              new Email(
                  user.getEmailId(),
                  String.format("Lunch is Ready for Today %s", booking.getDate()),
                  String.format(
                      "Hey %s %s,%n%nPFA Coupon for your today's meal %s with Option %s.%n%nCheers!!",
                      bookingModel.firstName(),
                      bookingModel.lastName(),
                      meal.getName(),
                      bookingModel.mealOption()),
                  bookingId + ".png",
                  new ByteArrayDataSource(
                      QRUtil.getQRCodeImage(bookingId, 500, 500), "application/octet-stream")));
        });
  }

  @Override
  @Transactional
  public MealsModel makeMealUnready(long mealId) {
    var meal = getMealById(mealId);
    if (!meal.isReady()) {
      log.error("Meal:{} is not Ready!", mealId);
      throw new InvalidMealOperation(String.format("Meal:%s is not Ready!", meal.getName()));
    }
    meal.markUnReady();
    return mealsRequestModelMapper.transform(meal);
  }

  @Override
  public List<MealsModel> getMealsAvailableForBookingWithAlreadyMarkedBookings(long userId) {
    var today = LocalDate.now();
    var mealList =
        mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(today)
            .stream()
            .map(meals -> mealsRequestModelMapper.transform(meals))
            .toList();

    if (!mealList.isEmpty()) {
      var bookingsList =
          bookingRepository
              .findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(today, userId)
              .stream()
              .map(bookings -> bookingResponseModelMapper.transform(bookings))
              .toList();

      mealList.stream()
          .filter(
              mealsModel ->
                  bookingsList.stream()
                      .anyMatch(bookings -> bookings.date().isEqual(mealsModel.getDate())))
          .forEach(
              mealsModel ->
                  mealsModel.getMealOptions().stream()
                      .filter(
                          mealOptionsModel ->
                              bookingsList.stream()
                                  .anyMatch(
                                      bookings ->
                                          bookings.mealOptionId().equals(mealOptionsModel.getId())))
                      .forEach(mealOptionsModel -> mealOptionsModel.setSelected(Boolean.TRUE)));
    }
    return mealList;
  }

  @Override
  public List<MealsModel> getAllMeals(boolean today) {
    List<Meals> result = null;
    var date = LocalDate.now();
    if (today) result = mealsRepository.findByDateGreaterThanEqualOrderByDateAsc(date);
    else result = mealsRepository.findByDateLessThanOrderByDateDesc(date);
    return !result.isEmpty()
        ? result.stream().map(meals -> mealsRequestModelMapper.transform(meals)).toList()
        : new ArrayList<>();
  }

  @Override
  public List<AvailableOptionsResponseModel> getAllAvailableOptionsForToday() {
    return availableBookingsRepository.findByDateOrderByCountDesc(LocalDate.now()).stream()
        .map(
            availableBookings ->
                bookingsAvailableOptionsResponseModelMapper.transform(availableBookings))
        .toList();
  }
}
