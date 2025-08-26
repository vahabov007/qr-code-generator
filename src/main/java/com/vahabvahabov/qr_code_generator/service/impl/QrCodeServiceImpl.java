package com.vahabvahabov.qr_code_generator.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.vahabvahabov.qr_code_generator.model.QrCodeRequest;
import com.vahabvahabov.qr_code_generator.service.QrCodeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Override
    public ResponseEntity<byte[]> generateQrCode(QrCodeRequest request) throws WriterException, IOException {
        String url = request.getUrl();

        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("The provided value is not a valid URL.");
        }

        int qrCode;
        if (request.getQrColor() != null) {
            qrCode = Color.decode(request.getQrColor()).getRGB();
        } else {
            qrCode = Color.BLACK.getRGB();
        }

        int bgCode;
        if (request.getBgColor() != null) {
            bgCode = Color.decode(request.getBgColor()).getRGB();
        } else {
            bgCode = Color.WHITE.getRGB();
        }

        if (qrCode == bgCode) {
            throw new IllegalArgumentException("QR code color cannot be the same as the background color.");
        }

        int width = 500;
        int height = 500;

        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, new MatrixToImageConfig(qrCode, bgCode));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "PNG", outputStream);
        byte[] data = outputStream.toByteArray();

        return ResponseEntity.ok().body(data);
    }

    @Override
    public ResponseEntity<byte[]> downloadQrCode(QrCodeRequest request) throws WriterException, IOException {
        String url = request.getUrl();

        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("The provided value is not a valid URL.");
        }

        int qrCode;
        if (request.getQrColor() != null) {
            qrCode = Color.decode(request.getQrColor()).getRGB();
        } else {
            qrCode = Color.BLACK.getRGB();
        }

        int bgCode;
        if (request.getBgColor() != null) {
            bgCode = Color.decode(request.getBgColor()).getRGB();
        } else {
            bgCode = Color.WHITE.getRGB();
        }

        if (qrCode == bgCode) {
            throw new IllegalArgumentException("QR code color cannot be the same as the background color.");
        }

        int width = 500;
        int height = 500;

        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, new MatrixToImageConfig(qrCode, bgCode));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "PNG", outputStream);
        byte[] data = outputStream.toByteArray();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", "qr_code.png");

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.IMAGE_PNG).body(data);
    }
}