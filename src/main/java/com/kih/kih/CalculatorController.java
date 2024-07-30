package com.kih.kih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping
    public String welcome() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/plus")
    public ResponseEntity<String> add(@RequestParam(required = false) Integer num1,
                                      @RequestParam(required = false) Integer num2) {
        if (num1 == null || num2 == null) {
            return new ResponseEntity<>("Ошибка: оба параметра должны быть указаны.", HttpStatus.BAD_REQUEST);
        }
        int result = calculatorService.add(num1, num2);
        return ResponseEntity.ok(String.format("%d + %d = %d", num1, num2, result));
    }
    @GetMapping("/minus")
    public ResponseEntity<String> subtract(@RequestParam(required = false) Integer num1,
                                           @RequestParam(required = false) Integer num2) {
        if (num1 == null || num2 == null) {
            return new ResponseEntity<>("Ошибка: оба параметра должны быть указаны.", HttpStatus.BAD_REQUEST);
        }
        int result = calculatorService.subtract(num1, num2);
        return ResponseEntity.ok(String.format("%d − %d = %d", num1, num2, result));
    }

    @GetMapping("/multiply")
    public ResponseEntity<String> multiply(@RequestParam(required = false) Integer num1,
                                           @RequestParam(required = false) Integer num2) {
        if (num1 == null || num2 == null) {
            return new ResponseEntity<>("Ошибка: оба параметра должны быть указаны.", HttpStatus.BAD_REQUEST);
        }
        int result = calculatorService.multiply(num1, num2);
        return ResponseEntity.ok(String.format("%d * %d = %d", num1, num2, result));
    }

    @GetMapping("/divide")
    public ResponseEntity<String> divide(@RequestParam(required = false) Integer num1,
                                         @RequestParam(required = false) Integer num2) {
        if (num1 == null || num2 == null) {
            return new ResponseEntity<>("Ошибка: оба параметра должны быть указаны.", HttpStatus.BAD_REQUEST);
        }
        try {
            double result = calculatorService.divide(num1, num2);
            return ResponseEntity.ok(String.format("%d / %d = %.2f", num1, num2, result));
        } catch (ArithmeticException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
