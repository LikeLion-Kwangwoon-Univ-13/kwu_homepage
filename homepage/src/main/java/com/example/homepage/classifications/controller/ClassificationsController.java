package com.example.homepage.classifications.controller;

import com.example.homepage.classifications.service.ClassificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/classifications")
@RequiredArgsConstructor
public class ClassificationsController {
    private final ClassificationsService classificationsService;
}
