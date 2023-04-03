package com.example.testbaseofsocks.controller;

import com.example.testbaseofsocks.dto.SocksDto;
import com.example.testbaseofsocks.service.ApiSocksService;


import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    ResponseEntity<Object> addSocks(@RequestBody() SocksDto properties)
    {
        apiSoksService.addSocksToDb(properties);
        return ResponseEntity.status(HttpStatus.OK).body("удалось добавить приход");
    }

    @PostMapping("/outcome")
    ResponseEntity<Object> decreaseSocks(@RequestBody SocksDto properties)
    {
        apiSoksService.decreaseSocks(properties);
        return ResponseEntity.status(HttpStatus.OK).body("удалось отгрузить носки");
    }

    @GetMapping()
    public ResponseEntity<String> quantitySocksFromWarehouse(@RequestParam(name = "color") String color,
                                                             @RequestParam(name = "operation") String operation,
                                                             @RequestParam(name = "cottonPart") Double cottonPart)
    {
        if(!color.matches("[a-zA-Z]+") || (!operation.equals("lessThan") && !operation.equals("moreThan") &&
            !operation.equals("equal"))){
        throw  new HttpMessageNotReadableException("параметры запроса отсутствуют или имеют некорректный формат");
    }

        return ResponseEntity.status(HttpStatus.OK).body(apiSoksService.quantitySocksFromWarehouse(color,
                operation, cottonPart));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handleException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("параметры запроса отсутствуют " +
                "или имеют некорректный формат"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException500() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage("произошла ошибка, " +
                "не зависящая от вызывающей стороны (например, база данных недоступна)"));
    }
}
