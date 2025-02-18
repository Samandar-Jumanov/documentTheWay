package com.dtw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// Occurs in runtime or when we use it
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameTakenException  extends  RuntimeException{

    private String message;

    public UsernameTakenException ( String message ){
          super(message);
    }

}
