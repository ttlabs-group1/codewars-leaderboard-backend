package io.turntabl.leaderboard.error;

import io.turntabl.leaderboard.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotAvailableException.class)
    public ResponseEntity<ResponseDTO> usernameNotAvailableExceptionHandler(UsernameNotAvailableException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ResponseDTO.builder()
                                .success(false)
                                .message(exception.getMessage())
                                .build()
                );
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDTO> userNotFoundExceptionHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                                .success(false)
                                .message(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(LogoutFailedException.class)
    public ResponseEntity<ResponseDTO> logoutFailedException(LogoutFailedException exception) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                                .success(false)
                                .message(exception.getMessage())
                                .build()
                );
    }
}
