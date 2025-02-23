package com.dtw.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException {

    private   String fieldName ;
    private Long fieldValue;
    private String resourceName;

    public ResourceNotFoundException( String  fieldName ,  String resourceName , Long fieldValue ){
        super(String.format("%s Not  found with %s : %s " , resourceName , fieldName , fieldValue) );
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
    }
}
