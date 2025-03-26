package com.example.schedule.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;

// 예외 처리 핸들러
@ControllerAdvice
public class ScheduleExceptionHandler {


    //valid 예외처리 메세지
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>("@Validated failed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    //valid 예외처리시 위반사항 모두 출력
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("@Valid failed: " + ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
    //제이슨 입력 잘못되었을시 (날짜타입) 오류 메세지
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("An error occurred: " + "올바른 날짜를 입력해 주세요", HttpStatus.BAD_REQUEST);
    }
    //경로 날짜 변수 잘못 입력시 오류 메세지
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("An error occurred: " + "올바른 주소를 입력해 주세요", HttpStatus.BAD_REQUEST);
    }


}
