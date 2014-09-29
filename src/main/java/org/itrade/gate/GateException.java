package org.itrade.gate;

/**
 * Created by dimapod on 29/09/14.
 */
public class GateException extends RuntimeException {
    public GateException() {
        super();
    }

    public GateException(String message) {
        super(message);
    }

    public GateException(String message, Throwable cause) {
        super(message, cause);
    }

    public GateException(Throwable cause) {
        super(cause);
    }

    protected GateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
