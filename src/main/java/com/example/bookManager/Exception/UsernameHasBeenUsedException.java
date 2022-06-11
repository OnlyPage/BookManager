package com.example.bookManager.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED)
public class UsernameHasBeenUsedException extends RuntimeException {
    public UsernameHasBeenUsedException(String message)
    {
        super(message);
    }
    public UsernameHasBeenUsedException()
    {
        super();
    }
    public UsernameHasBeenUsedException(String message,Throwable cause)
    {
        super(message, cause);
    }
}
