package xyz.subho.lunchbooking.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QRUtil {

  private QRUtil() {
    throw new IllegalArgumentException("Utility Class");
  }

  public static void generateQRCodeImage(String text, int width, int height, String filePath) {

    try {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix bitMatrix;
      bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
      Path path = FileSystems.getDefault().getPath(filePath);
      MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    } catch (WriterException | IOException e) {
      log.error("QR for Text:{}, could not e generated.", text);
    }
  }

  public static byte[] getQRCodeImage(String text, int width, int height) {

    byte[] pngData = new byte[0];
    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

    try {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
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
