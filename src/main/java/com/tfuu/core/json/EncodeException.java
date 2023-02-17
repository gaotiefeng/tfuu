package com.tfuu.core.json;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@SuppressWarnings("all")
public class EncodeException extends RuntimeException {

    public EncodeException(String message) {
        super(message);
    }

    public EncodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncodeException() {
    }
}