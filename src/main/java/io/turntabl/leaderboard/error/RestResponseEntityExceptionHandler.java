package io.turntabl.leaderboard.error;

import io.turntabl.leaderboard.dto.ResponseDTO;
import io.turntabl.leaderboard.exceptions.UserNotFoundException;
import io.turntabl.leaderboard.exceptions.UsernameNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotAvailableException.class)
    public ResponseEntity<ResponseDTO> usernameNotAvailableExceptionHandler(UsernameNotAvailableException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                                .success(false)
                                .message(exception.getMessage())
                                .build()
                );
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDTO> userNotFoundExceptionHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                                .success(false)
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO> userNotFoundOnCodewarsExceptionHandler(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message(exception.getMessage())
                        .build()
                );
    }
}
