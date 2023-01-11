package io.turntabl.leaderboard.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CommentTextFieldEmptyException extends RuntimeException{
    public CommentTextFieldEmptyException() {}

    public CommentTextFieldEmptyException(String message) {
        super(message);
    }

    public CommentTextFieldEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentTextFieldEmptyException(Throwable cause) {
        super(cause);
    }

}
