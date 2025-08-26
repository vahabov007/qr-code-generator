package com.vahabvahabov.qr_code_generator.controller.impl;

import com.vahabvahabov.qr_code_generator.controller.HomeController;
import org.springframework.stereotype.Controller;

@Controller
public class HomeControllerImpl implements HomeController {

    @Override
    public String getHomePage() {
        return "index";
    }
}
