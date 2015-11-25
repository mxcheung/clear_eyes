package com.mkyong.web.controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//..
@ControllerAdvice
public class BadExceptionControllerAdvice {
 
    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
 
        return "error";
    }
}