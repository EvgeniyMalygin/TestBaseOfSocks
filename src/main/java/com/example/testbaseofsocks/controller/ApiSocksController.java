package com.example.testbaseofsocks.controller;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.service.ApiSocksService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socks")
public class ApiSocksController {

    private final ApiSocksService apiSoksService;

    @PostMapping("/income")
    ResponseEntity<SocksDto> addSocks(@RequestPart() SocksDto properties) {
        return ResponseEntity.status(HttpStatus.OK).body(apiSoksService.addSocksToDb(properties));
    }


}
