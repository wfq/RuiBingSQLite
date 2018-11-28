package com.chen.ellen.ruibingsqlite.exception;

public class TableNotFoundException {

    private static final long serialVersionUID = 1L;
    public String ecpMess = "";

    public TableNotFoundException(){
        super();
    }

    public TableNotFoundException(String ecpMess){
        this.ecpMess = ecpMess;
    }

    public String getMessage() {

        return this.ecpMess;

    }

}
