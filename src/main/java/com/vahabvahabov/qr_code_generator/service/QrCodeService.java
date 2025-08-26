package com.vahabvahabov.qr_code_generator.service;

import com.google.zxing.WriterException;
import com.vahabvahabov.qr_code_generator.model.QrCodeRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface QrCodeService {

    public ResponseEntity<byte[]> generateQrCode(QrCodeRequest request) throws WriterException, IOException;
    public ResponseEntity<byte[]> downloadQrCode(QrCodeRequest request) throws WriterException, IOException;
}
