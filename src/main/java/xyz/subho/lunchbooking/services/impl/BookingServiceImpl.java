package xyz.subho.lunchbooking.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.repositories.BookingRepository;
import xyz.subho.lunchbooking.services.BookingService;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

  @Autowired private BookingRepository bookingRepository;

  @Override
  public long createBooking(long mealOptionId, long userId) {
    return 0;
  }
}
