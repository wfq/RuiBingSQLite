package com.chen.ellen.ruibingsqlite.exception;

public class LibraryNotFoundException {

    private static final long serialVersionUID = 1L;
    public String ecpMess = "";

    public LibraryNotFoundException(){
        super();
    }

    public LibraryNotFoundException(String ecpMess){
        this.ecpMess = ecpMess;
    }

    public String getMessage() {

        return this.ecpMess;

    }

}
