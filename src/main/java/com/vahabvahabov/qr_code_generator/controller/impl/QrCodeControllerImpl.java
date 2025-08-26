package com.vahabvahabov.qr_code_generator.controller.impl;

import com.google.zxing.WriterException;
import com.vahabvahabov.qr_code_generator.controller.QrCodeController;
import com.vahabvahabov.qr_code_generator.model.QrCodeRequest;
import com.vahabvahabov.qr_code_generator.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;

@Controller
public class QrCodeControllerImpl implements QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @Override
    @GetMapping(value = "/generate-qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQrCode(@ModelAttribute QrCodeRequest request) throws IOException, WriterException {
        return qrCodeService.generateQrCode(request);
    }

    @Override
    @GetMapping("/download-qr")
    public ResponseEntity<byte[]> downloadQrCode(@ModelAttribute QrCodeRequest request) throws IOException, WriterException {
        return qrCodeService.downloadQrCode(request);
    }
}
