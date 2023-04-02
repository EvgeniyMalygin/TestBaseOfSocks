package com.example.testbaseofsocks.controller;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.service.ApiSocksService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class ApiSocksController {

    private final ApiSocksService apiSoksService;

    public ApiSocksController(ApiSocksService apiSoksService)
    {
        this.apiSoksService = apiSoksService;
    }

    @PostMapping("/income")
    ResponseEntity<SocksDto> addSocks(@RequestBody() SocksDto properties)
    {
        return ResponseEntity.status(HttpStatus.OK).body(apiSoksService.addSocksToDb(properties));
    }

    @PostMapping("/outcome")
    ResponseEntity<SocksDto> decreaseSocks(@RequestBody SocksDto properties)
    {
        return ResponseEntity.status(HttpStatus.OK).body(apiSoksService.decreaseSocks(properties));
    }

    @GetMapping()
    public ResponseEntity<String> quantitySocksFromWarehouse(@RequestParam(name = "color") String color,
                                                             @RequestParam(name = "operation") String operation,
                                                             @RequestParam(name = "cottonPart") Double cottonPart)
    {
        return ResponseEntity.status(HttpStatus.OK).body(apiSoksService.quantitySocksFromWarehouse(color,
                operation, cottonPart));
    }


}
