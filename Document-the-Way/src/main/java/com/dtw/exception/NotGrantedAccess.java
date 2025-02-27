package com.dtw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotGrantedAccess extends  RuntimeException {

    private  String message;
    private  String  resourceName;

    public  NotGrantedAccess(String message , String resourceName){
        this.message = message;
        this.resourceName =resourceName;
    }
}
