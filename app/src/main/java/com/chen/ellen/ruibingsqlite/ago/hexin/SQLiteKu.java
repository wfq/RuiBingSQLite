package com.chen.ellen.ruibingsqlite.ago.hexin;

import java.util.ArrayList;
import java.util.List;

public class SQLiteKu {
	
	public static enum TableManager{
		SYSTEM_TABLE_MANAGER,YOURSELF_TABLE_MANAGER;
	}
	
	public SQLiteKu(String kuName){
		this.kuName = kuName;
	}
	
	public SQLiteKu(String kuName,String sqlTableManagerName){
		this.kuName = kuName;
		this.sqlTableManagerName = sqlTableManagerName;
	}
	
	
	private String sqlTableManagerName = "sqlTableManager";
	
	public String getSqlTableManagerName() {
		return sqlTableManagerName;
	}

	public void setSqlTableManagerName(String sqlTableManagerName) {
		this.sqlTableManagerName = sqlTableManagerName;
	}

	private String kuName;

	private List<SQLiteTable> sqLiteTables = new ArrayList<>();;

	public List<SQLiteTable> getSqLiteTables() {
		return sqLiteTables;
	}

	public String getKuName() {
		return kuName;
	}

	public void setKuName(String kuName) {
		this.kuName = kuName;
	}

	public void addSQLiteTable(SQLiteTable sqLiteTable){
		for(SQLiteTable sqLiteTable2:sqLiteTables){
			if(sqLiteTable.getTableName().equals(sqLiteTable2.getTableName())
				|| sqLiteTable.getTableName().equals(sqlTableManagerName)){
				return;
			}
		}
		sqLiteTables.add(sqLiteTable);
	}
	
}
