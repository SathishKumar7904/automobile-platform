package com.automobile.enquiry.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ReferencedResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> handleNotFound(ReferencedResourceNotFoundException ex){return Map.of("timestamp",Instant.now().toString(),"status",404,"error","Not Found","message",ex.getMessage());}
    @ExceptionHandler(InvalidDealerAssignmentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String,Object> handleInvalidDealer(InvalidDealerAssignmentException ex){return Map.of("timestamp",Instant.now().toString(),"status",422,"error","Unprocessable Entity","message",ex.getMessage());}
    @ExceptionHandler(DependencyServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Map<String,Object> handleDependency(DependencyServiceUnavailableException ex){return Map.of("timestamp",Instant.now().toString(),"status",503,"error","Service Unavailable","message",ex.getMessage());}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleValidation(MethodArgumentNotValidException ex){
        Map<String,String> errors=new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        return Map.of("timestamp",Instant.now().toString(),"status",400,"error","Bad Request","message","Request contains invalid fields","fields",errors);
    }
}