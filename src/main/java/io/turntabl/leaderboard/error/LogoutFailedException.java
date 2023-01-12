package io.turntabl.leaderboard.error;

public class LogoutFailedException extends RuntimeException{

    public LogoutFailedException() {
        super();
    }

    public LogoutFailedException(String message) {
        super(message);
    }

    public LogoutFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogoutFailedException(Throwable cause) {
        super(cause);
    }

    protected LogoutFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
