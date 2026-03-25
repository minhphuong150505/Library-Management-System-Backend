package com.phuong.exception;

import com.phuong.modal.Genre;
import com.phuong.response.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(GenreException.class)
    public ResponseEntity<ApiResponse> handleGenreException(GenreException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(ex.getMessage(), false));
    }
}
