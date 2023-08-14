package com.example.todoapi.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.ResourceNotFoundError;
import com.example.todoapi.service.task.TaskEntityNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice // advice差し込む(今回はエラーが発声したときに差し込みたい)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(TaskEntityNotFoundException.class)
  public ResponseEntity<ResourceNotFoundError> handlerTaskEntityNotFoundException(TaskEntityNotFoundException e) {
    var error = new ResourceNotFoundException();
    error.setDetail(e.getMessage());
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<BadRequestError> handleConstraintViolationException(
      ConstraintViolationException ex) {
    var error = BadReuqestErrorCreator.from(ex);
    return ResponseEntity.badRequest().body(new BadRequestError(error));
  }

  @Override
  protected ResponseEntity<BadRequestError> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    var error = BadReuqestErrorCreator.from(ex);
    return ResponseEntity.badRequest().body(new BadRequestError(error));
  }

}
