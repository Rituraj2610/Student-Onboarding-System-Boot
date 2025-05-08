package com.rituraj.candidateOnboardingSystem.exception;

import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DuplicateEntityException.class})
    public ResponseEntity<ErrorResponse> handleDuplicateEntityException(DuplicateEntityException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(ApiStatus.CONFLICT)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {EntityInsertException.class})
    public ResponseEntity<ErrorResponse> handleEntityInsertException(EntityInsertException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(ApiStatus.UNPROCESSABLE_ENTITY)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(ApiStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CandidateApplicationRejectedException.class})
    public ResponseEntity<ErrorResponse> handleCandidateApplicationRejectedException(CandidateApplicationRejectedException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(ApiStatus.CONFLICT)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
