package com.example.isuyo_000.activities.JSon;

/**
 * Created by McLovin on 11/30/2017.
 */

public class JSonManagerException extends Exception{
    private ErrorType error;

    public enum ErrorType{
        File,
        State,
        JSonParsing
    }

    public JSonManagerException(ErrorType e){
        super();
        this.error = e;
    }

    public JSonManagerException(ErrorType e , String m){
        super(m);
        this.error = e;
    }

    public JSonManagerException(ErrorType e ,  String m, String message){
        super( m + "\n" + message);
        this.error = e;
    }

    public ErrorType getErrorType(){
        return error;
    }


}
