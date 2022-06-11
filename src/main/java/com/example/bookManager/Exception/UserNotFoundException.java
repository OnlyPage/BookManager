package com.example.bookManager.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
    public UserNotFoundException()
    {
        super();
    }
    public UserNotFoundException(String message,Throwable cause)
    {
        super(message, cause);
    }
}
