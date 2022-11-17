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

package xyz.subho.lunchbooking.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QRUtil {

  private QRUtil() {
    throw new IllegalArgumentException("Utility Class");
  }

  public static byte[] getQRCodeImage(String text, int width, int height) {

    byte[] pngData = new byte[0];
    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

    Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    hints.put(EncodeHintType.MARGIN, 1);
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
    hints.put(EncodeHintType.QR_COMPACT, true);
    hints.put(EncodeHintType.DATA_MATRIX_COMPACT, true);

    try {
      BitMatrix bitMatrix =
          new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
      MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);
      MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
      pngData = pngOutputStream.toByteArray();
      pngOutputStream.close();
    } catch (WriterException | IOException e) {
      log.error("QR for Text:{}, could not e generated.", text);
    }
    return pngData;
  }
}
