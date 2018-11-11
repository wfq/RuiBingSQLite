package com.chen.ellen.ruibingsqlite.ago.hexin;

import android.content.Context;

public class SQLiteSetting {
	
	
	public SQLiteSetting(Context context,SQLiteKu sqLiteKu,int sqliteVersion){
		
		this.context = context;
		this.sqLiteKu = sqLiteKu;
		this.sqliteVersion = sqliteVersion;
		
	}

	private Context context;

	private int sqliteVersion;
	
	
	private SQLiteKu sqLiteKu;
	
	
	
	public SQLiteKu getSqLiteKu() {
		return sqLiteKu;
	}


	public void setSqLiteKu(SQLiteKu sqLiteKu) {
		this.sqLiteKu = sqLiteKu;
	}


	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	
	public int getSqliteVersion() {
		return sqliteVersion;
	}
	public void setSqliteVersion(int sqliteVersion) {
		this.sqliteVersion = sqliteVersion;
	}

}
