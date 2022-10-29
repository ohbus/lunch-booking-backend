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

import java.security.SecureRandom;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.OtpEntity;
import xyz.subho.lunchbooking.models.OtpRequestModel;
import xyz.subho.lunchbooking.models.OtpResponseModel;
import xyz.subho.lunchbooking.repositories.OtpRepository;
import xyz.subho.lunchbooking.services.OtpService;

@Slf4j
@Service
public class OtpServiceImpl implements OtpService {

  @Value("${otp.validity.mins:2}")
  private String expiryMilliseconds;

  @Autowired private OtpRepository otpRepository;

  @Override
  public OtpResponseModel createOtp(long userId) {
    var newOtp = new OtpEntity(userId);
    newOtp.setExpiresAt(LocalDateTime.now().plusMinutes(Long.parseLong(expiryMilliseconds)));
    newOtp.setOtp(new SecureRandom().nextInt(100000, 999999));
    return null;
  }

  @Override
  public OtpResponseModel resendOtp(long salt) {
    return null;
  }

  @Override
  public boolean validateOtp(OtpRequestModel requestModel) {
    var otpEntityOpt = otpRepository.findById(requestModel.getSalt());
    if (otpEntityOpt.isPresent()) {
      var otp = otpEntityOpt.get();
      if (otp.getOtp().equals(requestModel.getOtp())
          && otp.isSent()
          && !otp.isExpired()
          && !otp.isVerified()) {
        otp.verify();
        log.debug(
            "OTP:{} has been verified for User ID:{}", requestModel.getOtp(), otp.getUserId());
        return true;
      }
      return false;
    }
    return false;
  }
}
