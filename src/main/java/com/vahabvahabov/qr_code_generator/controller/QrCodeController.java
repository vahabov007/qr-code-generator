package com.vahabvahabov.qr_code_generator.controller;

import com.google.zxing.WriterException;
import com.vahabvahabov.qr_code_generator.model.QrCodeRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface QrCodeController {

    public ResponseEntity<byte[]> generateQrCode(QrCodeRequest request) throws IOException, WriterException;
    public ResponseEntity<byte[]> downloadQrCode(QrCodeRequest request) throws IOException, WriterException;
}
