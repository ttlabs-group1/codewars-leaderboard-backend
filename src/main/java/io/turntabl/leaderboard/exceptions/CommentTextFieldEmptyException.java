package io.turntabl.leaderboard.exceptions;

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
