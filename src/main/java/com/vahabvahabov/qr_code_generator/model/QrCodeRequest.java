package com.vahabvahabov.qr_code_generator.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrCodeRequest {
    private String url;
    private String qrColor;
    private String bgColor;
}
