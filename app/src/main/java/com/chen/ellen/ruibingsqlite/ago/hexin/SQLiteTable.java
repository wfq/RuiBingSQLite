package com.chen.ellen.ruibingsqlite.ago.hexin;


public class SQLiteTable {

	private String tableName;

	private String keyShuXingName;
	private Class<?> objClass;
	
	private String weiYiShuXingName;
	
	private String paiXuShuXingName;
	
	

	public SQLiteTable(String tableName, String keyShuXingName, Class<?> objClass, String weiYiShuXingName,
			String paiXuShuXingName) {
		super();
		this.tableName = tableName;
		this.keyShuXingName = keyShuXingName;
		this.objClass = objClass;
		this.weiYiShuXingName = weiYiShuXingName;
		this.paiXuShuXingName = paiXuShuXingName;
	}
	
	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getKeyShuXingName() {
		return keyShuXingName;
	}

	public void setKeyShuXingName(String keyShuXingName) {
		this.keyShuXingName = keyShuXingName;
	}

	public Class<?> getObjClass() {
		return objClass;
	}

	public void setObjClass(Class<?> objClass) {
		this.objClass = objClass;
	}

	public String getWeiYiShuXingName() {
		return weiYiShuXingName;
	}

	public void setWeiYiShuXingName(String weiYiShuXingName) {
		this.weiYiShuXingName = weiYiShuXingName;
	}

	public String getPaiXuShuXingName() {
		return paiXuShuXingName;
	}

	public void setPaiXuShuXingName(String paiXuShuXingName) {
		this.paiXuShuXingName = paiXuShuXingName;
	}
	
	
	
	

}
