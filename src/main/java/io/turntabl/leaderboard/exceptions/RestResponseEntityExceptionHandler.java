package io.turntabl.leaderboard.exceptions;

import io.turntabl.leaderboard.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> userAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.OK)
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

    @ExceptionHandler(CommentTextFieldEmptyException.class)
    public ResponseEntity<ResponseDTO> commentTextFieldEmptyExceptionHandler(CommentTextFieldEmptyException exception) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message(exception.getMessage())
                        .build()
                );
    }
}
