package com.chen.ellen.ruibingsqlite.ago.exception;

public class SQLiteTableNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	public String ecpMess = "";

	public SQLiteTableNotFoundException(){
			   super();
    }

	public SQLiteTableNotFoundException(String ecpMess){
			   this.ecpMess = ecpMess;
	}

	public String getMessage() {

		return this.ecpMess;

	}

}
