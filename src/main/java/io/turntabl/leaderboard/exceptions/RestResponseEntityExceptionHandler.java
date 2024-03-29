package io.turntabl.leaderboard.exceptions;

import io.turntabl.leaderboard.dto.ResponseDTO;
import io.turntabl.leaderboard.error.LogoutFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> userAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message(exception.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(CommentTextFieldEmptyException.class)
    public ResponseEntity<ResponseDTO> commentTextFieldEmptyExceptionHandler(CommentTextFieldEmptyException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message(exception.getMessage())
                        .build()
                );
    }


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
                        .build());
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
