package com.chen.ellen.ruibingsqlite.ago.hexin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.chen.ellen.ruibingsqlite.ago.exception.SQLiteTableNotFoundException;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUtil extends SQLiteOpenHelper {

	private SQLiteSetting sqLiteSetting;

	
	
	public SQLiteSetting getSqLiteSetting() {
		return sqLiteSetting;
	}

	public void setSqLiteSetting(SQLiteSetting sqLiteSetting) {
		this.sqLiteSetting = sqLiteSetting;
	}

	public SQLiteUtil(SQLiteSetting sqLiteSetting) {

		super(sqLiteSetting.getContext(), sqLiteSetting.getSqLiteKu().getKuName(), null,
				sqLiteSetting.getSqliteVersion());
		this.sqLiteSetting = sqLiteSetting;

	}

	private ArrayList<SQLiteTable> createTable(SQLiteDatabase db) {
		
		ArrayList<SQLiteTable> sqLiteTables = new ArrayList<>();

		for (SQLiteTable sqLiteTable : sqLiteSetting.getSqLiteKu().getSqLiteTables()) {

			
			   
				if(onCreateTable(sqLiteTable, db)){
					sqLiteTables.add(sqLiteTable);
				}
			

		}
		
		return sqLiteTables;

	}

	private String getSQlStringType(Class<?> ziDuanJavaType) {

		String sqlType = "";

		if (ziDuanJavaType == Byte.class || ziDuanJavaType.getName().equals("byte")) {
			sqlType = "integer";
		} else if (ziDuanJavaType == Short.class || ziDuanJavaType.getName().equals("short")) {
			sqlType = "integer";
		} else if (ziDuanJavaType == Integer.class || ziDuanJavaType.getName().equals("int")) {
			sqlType = "integer";
		} else if (ziDuanJavaType == Long.class || ziDuanJavaType.getName().equals("long")) {
			sqlType = "bigint";
		} else if (ziDuanJavaType == Float.class || ziDuanJavaType.getName().equals("float")) {
			sqlType = "real";
		} else if (ziDuanJavaType == Double.class || ziDuanJavaType.getName().equals("double")) {
			sqlType = "real";
		} else if (ziDuanJavaType == Boolean.class || ziDuanJavaType.getName().equals("boolean")) {
			sqlType = "integer";
		} else if (ziDuanJavaType == Character.class || ziDuanJavaType.getName().equals("char")) {
			sqlType = "text";
		} else if (ziDuanJavaType == String.class) {
			sqlType = "text";
		} else {
			sqlType = "no";
		}

		return sqlType;
	}


	public void add(SQLiteTable sqLiteTable, Object data) {
		// 1.���ȴ���һ��д���db����

		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		// ��Ҫ��ȡClass����
		Class<?> tableClass = data.getClass();

		// ��ȡ�ֶε���������
		// ������ÿһ������
		Field[] fields = tableClass.getDeclaredFields();
		for (Field field : fields) {
			// �ж���������Ƿ�������ݿ�淶
			field.setAccessible(true);
			if (!getSQlStringType(field.getType()).equals("no")) {
				try {
					if (field.getType() == Byte.class || field.getType().getName().equals("byte")) {

						contentValues.put(field.getName(), field.getByte(data));

					} else if (field.getType() == Short.class || field.getType().getName().equals("short")) {
						contentValues.put(field.getName(), field.getShort(data));
					} else if (field.getType() == Integer.class || field.getType().getName().equals("int")) {
						contentValues.put(field.getName(), field.getInt(data));
					} else if (field.getType() == Long.class || field.getType().getName().equals("long")) {
						contentValues.put(field.getName(), field.getLong(data));
					} else if (field.getType() == Float.class || field.getType().getName().equals("float")) {
						contentValues.put(field.getName(), field.getFloat(data));
					} else if (field.getType() == Double.class || field.getType().getName().equals("double")) {
						contentValues.put(field.getName(), field.getDouble(data));
					} else if (field.getType() == Character.class || field.getType().getName().equals("char")) {
						contentValues.put(field.getName(), field.getChar(data) + "");
					} else if (field.getType() == Boolean.class || field.getType().getName().equals("boolean")) {
						contentValues.put(field.getName(), field.getBoolean(data) ? 1 : 0);
					} else if (field.getType() == String.class) {
						contentValues.put(field.getName(), (String) field.get(data));
					}

					// �Ƴ����I
					if (field.getName().equals(sqLiteTable.getKeyShuXingName())) {
						contentValues.remove(field.getName());
					}

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		db.insert(sqLiteTable.getTableName(), null, contentValues);
		contentValues.clear();
		// db.close();

	}

	/**
	 * 
	 * ��������
	 * 
	 * @param sqLiteTable
	 *            Ҫ��ӵ����ݶ�Ӧ�ı����
	 * @param data
	 *            һ�����ݶ���
	 */
	private void add(SQLiteDatabase db,SQLiteTable sqLiteTable, Object data) {
		// 1.���ȴ���һ��д���db����

		
		
		ContentValues contentValues = new ContentValues();

		// ��Ҫ��ȡClass����
		Class<?> tableClass = data.getClass();

		// ��ȡ�ֶε���������
		// ������ÿһ������
		Field[] fields = tableClass.getDeclaredFields();
		for (Field field : fields) {
			// �ж���������Ƿ�������ݿ�淶
			field.setAccessible(true);
			if (!getSQlStringType(field.getType()).equals("no")) {
				try {
					if (field.getType() == Byte.class || field.getType().getName().equals("byte")) {

						contentValues.put(field.getName(), field.getByte(data));

					} else if (field.getType() == Short.class || field.getType().getName().equals("short")) {
						contentValues.put(field.getName(), field.getShort(data));
					} else if (field.getType() == Integer.class || field.getType().getName().equals("int")) {
						contentValues.put(field.getName(), field.getInt(data));
					} else if (field.getType() == Long.class || field.getType().getName().equals("long")) {
						contentValues.put(field.getName(), field.getLong(data));
					} else if (field.getType() == Float.class || field.getType().getName().equals("float")) {
						contentValues.put(field.getName(), field.getFloat(data));
					} else if (field.getType() == Double.class || field.getType().getName().equals("double")) {
						contentValues.put(field.getName(), field.getDouble(data));
					} else if (field.getType() == Character.class || field.getType().getName().equals("char")) {
						contentValues.put(field.getName(), field.getChar(data) + "");
					} else if (field.getType() == Boolean.class || field.getType().getName().equals("boolean")) {
						contentValues.put(field.getName(), field.getBoolean(data) ? 1 : 0);
					} else if (field.getType() == String.class) {
						contentValues.put(field.getName(), (String) field.get(data));
					}

					// �Ƴ����I
					if (field.getName().equals(sqLiteTable.getKeyShuXingName())) {
						contentValues.remove(field.getName());
					}

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		db.insert(sqLiteTable.getTableName(), null, contentValues);
		contentValues.clear();
		//db.close();

	}

	public void deleteDataById(SQLiteTable sqLiteTable, int id) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(sqLiteTable.getTableName(), sqLiteTable.getKeyShuXingName() + " == ?", new String[] { id + "" });

		// db.close();

	}

	/**
	 * ɾ������ͨ��Ψһ����ֵ
	 * 
	 * @param sqLiteTable
	 * @param obj
	 */
	public void deleteData(SQLiteTable sqLiteTable, Object obj) {

		SQLiteDatabase db = this.getWritableDatabase();

		Class<?> tableClass = obj.getClass();

		String deleteValue = "";
		// ��ȡ�ֶε���������
		// ������ÿһ������
		Field[] fields = tableClass.getDeclaredFields();
		for (Field field : fields) {
			// �ж���������Ƿ�������ݿ�淶
			field.setAccessible(true);
			if (!getSQlStringType(field.getType()).equals("no")
					&& field.getName().equals(sqLiteTable.getWeiYiShuXingName())) {
				try {
					if (field.getType() == Byte.class || field.getType().getName().equals("byte")) {

						deleteValue = field.getInt(obj) + "";

					} else if (field.getType() == Short.class || field.getType().getName().equals("short")) {

						deleteValue = field.getShort(obj) + "";

					} else if (field.getType() == Integer.class || field.getType().getName().equals("int")) {

						deleteValue = field.getInt(obj) + "";

					} else if (field.getType() == Long.class || field.getType().getName().equals("long")) {

						deleteValue = field.getLong(obj) + "";

					} else if (field.getType() == Float.class || field.getType().getName().equals("float")) {

						deleteValue = field.getFloat(obj) + "";

					} else if (field.getType() == Double.class || field.getType().getName().equals("double")) {

						deleteValue = field.getDouble(obj) + "";

					} else if (field.getType() == Character.class || field.getType().getName().equals("char")) {

						deleteValue = field.getChar(obj) + "";

					} else if (field.getType() == Boolean.class || field.getType().getName().equals("boolean")) {

						deleteValue = (field.getBoolean(obj)?1:0) + "";

					} else if (field.getType() == String.class) {

						deleteValue = (String) field.get(obj);

					}

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		db.delete(sqLiteTable.getTableName(), sqLiteTable.getWeiYiShuXingName() + " == ?", new String[] { deleteValue });

		// db.close();

	}

	/**
	 * ɾ����
	 * 
	 * @param sqLiteTable
	 */
	public void deleteTable(SQLiteTable sqLiteTable) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE " + sqLiteTable.getTableName() + ";");// ɾ���˱�
		// db.close();
	}

	/**
	 * ������ -->�ṩ����ʼ��������ʹ��
	 * 
	 * @param sqLiteTable
	 */
	private boolean onCreateTable(SQLiteTable sqLiteTable, SQLiteDatabase db) {

		Class<?> tableClass = sqLiteTable.getObjClass();
		// ������ÿһ������
		Field[] fields = tableClass.getDeclaredFields();

		// ���ڶ����Խ������Ե�����

		// ������Ҫ���Ե����ֺ�����
		String sqlString = "";
		for (Field field : fields) {
			field.setAccessible(true);// ��ȡ����Ȩ��-->private,protected
			String ziDuanName = field.getName();
			String ziDuanType = getSQlStringType(field.getType());
			if (!ziDuanType.equals("no")) {
				if (ziDuanName.equals(sqLiteTable.getKeyShuXingName() == null ? "" : sqLiteTable.getKeyShuXingName())) {

					// ������Ϊ����
					// "id integer primary key autoincrement,"
					if (!ziDuanType.equals("integer")) {

						// ��������׳�һ��������Ϊinteger�����쳣

					} else {
						sqlString = sqlString + ziDuanName + " " + ziDuanType + " primary key autoincrement,";
					}

				} else {
					sqlString = sqlString + ziDuanName + " " + ziDuanType + ",";
				}

			}

		}

		sqlString = sqlString.substring(0, sqlString.length() - 1);

		sqlString = "create table " + sqLiteTable.getTableName() + "(" + sqlString + ")";

		try{
		db.execSQL(sqlString);
		}catch(SQLException e){
			return false;
		}
		
		return true;
		
		// db.close();

	}

	/**
	 * ������ --> �ṩ���κ�ʱ��
	 * 
	 * @param sqLiteTable
	 */
	public void onCreateTable(SQLiteTable sqLiteTable) {

		// ������ﴴ���ظ����ֵı�ֱ������
		if (isHaveThisTable(sqLiteTable.getTableName())) {
			return;
		}

		SQLiteDatabase db = this.getWritableDatabase();

		Class<?> tableClass = sqLiteTable.getObjClass();
		// ������ÿһ������
		Field[] fields = tableClass.getDeclaredFields();

		// ���ڶ����Խ������Ե����� 1.0.1

		// ������Ҫ���Ե����ֺ�����
		String sqlString = "";
		for (Field field : fields) {
			field.setAccessible(true);// ��ȡ����Ȩ��-->private,protected
			String ziDuanName = field.getName();
			String ziDuanType = getSQlStringType(field.getType());
			if (!ziDuanType.equals("no")) {
				if (ziDuanName.equals(sqLiteTable.getKeyShuXingName() == null ? "" : sqLiteTable.getKeyShuXingName())) {

					// ������Ϊ����
					// "id integer primary key autoincrement,"
					if (!ziDuanType.equals("integer")) {

						// ��������׳�һ��������Ϊinteger�����쳣

					} else {
						sqlString = sqlString + ziDuanName + " " + ziDuanType + " primary key autoincrement,";
					}

				} else {
					sqlString = sqlString + ziDuanName + " " + ziDuanType + ",";
				}

			}

		}

		sqlString = sqlString.substring(0, sqlString.length() - 1);

		sqlString = "create table " + sqLiteTable.getTableName() + "(" + sqlString + ")";

		try {
			db.execSQL(sqlString);
		} catch (SQLiteException e) {
			// TODO: handle exception
			
		}
		
		// db.close();
		
		//��ӱ����ݵ������
		addTableToTableManger(db,sqLiteTable);

	}

	private boolean isHaveThisTable(String newTableName) {

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.query("sqlite_sequence", null, null, null, null, null, null);

		if (cursor.moveToFirst()) {

			do {

				if (cursor.getString(cursor.getColumnIndex("name")).equals(newTableName)) {
					return true;
				}

			} while (cursor.moveToNext());

		}

		return false;
	}
	
	/**
	 * ���ظÿ������д����ı�����
	 * @return
	 */
	public ArrayList<SQLiteTable> getAllTable(SQLiteKu.TableManager tableManger){
		
		if(tableManger == SQLiteKu.TableManager.SYSTEM_TABLE_MANAGER){
			return getAllTable("sqlite_sequence");
		}else if(tableManger == SQLiteKu.TableManager.YOURSELF_TABLE_MANAGER){
			return getAllTable(sqLiteSetting.getSqLiteKu().getSqlTableManagerName());
		}else{
			return null;
		}
		
		
		
	}
	
	
	private ArrayList<SQLiteTable> getAllTable(String tableName){
		
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.query(tableName, null, null, null, null, null, null);
		
		ArrayList<SQLiteTable> sqLiteTables = new ArrayList<>();

		if (cursor.moveToFirst()) {

			do {

				String name = cursor.getString(cursor.getColumnIndex("name"));
				SQLiteTable sqLiteTable = new SQLiteTable(null,null,null,null,null);
				sqLiteTable.setTableName(name);
				
				//�����жϱ��Ƿ����
				
				sqLiteTables.add(sqLiteTable);
					
				
			} while (cursor.moveToNext());

		}
		
		return sqLiteTables;
		
	}
	

	/**
	 * ��ɾ�������
	 * 
	 * @param sqLiteTable
	 * @param oldData
	 * @param newData
	 */
	public void upDate(SQLiteTable sqLiteTable, Object oldData, Object newData) {

		//���ж�oldData�����ݿ��д������
		
		deleteData(sqLiteTable, oldData);
		add(sqLiteTable, newData);

	}
		
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> serachAll(SQLiteTable sqLiteTable) throws SQLiteTableNotFoundException {
		

		if(isHaveThisTable(sqLiteTable.getTableName())){
			throw new SQLiteTableNotFoundException("not found table exception!");
		}
		
		ArrayList<T> dataList = new ArrayList<>();

		Class<?> objClass = sqLiteTable.getObjClass();

		Field[] fields = objClass.getDeclaredFields();

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.query(sqLiteTable.getTableName(), null, null, null, null, null,
				sqLiteTable.getPaiXuShuXingName());

		Constructor<?> con1;

		try {
			con1 = objClass.getConstructor();

			if (cursor.moveToFirst()) {

				do {
					Object obj = con1.newInstance();

					// ���ڰ����е�����װ����obj��
					// ������ÿһ������
					for (Field field : fields) {
						field.setAccessible(true);// ��ȡ����Ȩ��-->private,protected
						//String ziDuanName = field.getName();
						String ziDuanType = getSQlStringType(field.getType());
						if (!ziDuanType.equals("no")) {
							if (field.getType() == Byte.class || field.getType().getName().equals("byte")) {
								field.set(obj, cursor.getInt(cursor.getColumnIndex(field.getName())));
							} else if (field.getType() == Short.class || field.getType().getName().equals("short")) {
								field.set(obj, cursor.getShort(cursor.getColumnIndex(field.getName())));
							} else if (field.getType() == Integer.class || field.getType().getName().equals("int")) {
								field.set(obj, cursor.getInt(cursor.getColumnIndex(field.getName())));
							} else if (field.getType() == Long.class || field.getType().getName().equals("long")) {
								field.set(obj, cursor.getLong(cursor.getColumnIndex(field.getName())));
							} else if (field.getType() == Float.class || field.getType().getName().equals("float")) {
								field.set(obj, cursor.getFloat(cursor.getColumnIndex(field.getName())));
							} else if (field.getType() == Double.class || field.getType().getName().equals("double")) {
								field.set(obj, cursor.getDouble((cursor.getColumnIndex(field.getName()))));
							} else if (field.getType() == Character.class || field.getType().getName().equals("char")) {
								field.set(obj, cursor.getString(cursor.getColumnIndex(field.getName())));
							} else if (field.getType() == Boolean.class
									|| field.getType().getName().equals("boolean")) {
								field.set(obj,
										cursor.getInt(cursor.getColumnIndex(field.getName())) == 1 ? true : false);
							} else if (field.getType() == String.class) {

								field.set(obj, cursor.getString(cursor.getColumnIndex(field.getName())));

							}
						}

					}

					dataList.add((T) obj);

				} while (cursor.moveToNext());

			} else {
				return dataList;
			}

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataList;
	}
	
	
	public void startCreate(){
		SQLiteDatabase db = this.getWritableDatabase();
	}
	
	
	private void createTableManagerTable(SQLiteDatabase db){
		
		
	

		SQLiteTable sqLiteTable = new SQLiteTable(sqLiteSetting.getSqLiteKu().getSqlTableManagerName(),
				"id", BigSQLiteTable.class, null, null);
		
		onCreateTable(sqLiteTable, db);
		
	}
	
	
	private void addTableToTableManger(SQLiteDatabase db,SQLiteTable sqLiteTable){
		
		BigSQLiteTable bigSQLiteTable = new BigSQLiteTable();
		bigSQLiteTable.setClassName(sqLiteTable.getObjClass().getName());
		bigSQLiteTable.setGuanXi("�޹�ϵ");
		bigSQLiteTable.setName(sqLiteTable.getTableName());
		bigSQLiteTable.setSeq(0);
		
		SQLiteTable sqLiteTable1 = new SQLiteTable(sqLiteSetting.getSqLiteKu().getSqlTableManagerName(),
			 null, BigSQLiteTable.class, null, null);
		
		add(db, sqLiteTable1, bigSQLiteTable);
		
	}
	
	private SQLiteTable getSQLiteTabFromTableManager(String tableName){
		
		SQLiteTable sqLiteTable = null;
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.query(sqLiteSetting.getSqLiteKu().getSqlTableManagerName(), null, null, null, null, null,
				null);
		
		if(cursor.moveToFirst()){
			
			do {
				
				if(cursor.getString(cursor.getColumnIndex("name")).equals(tableName)){
					//sqLiteTable = new SQLiteTable(tableName, null, objClass, weiYiShuXingName, paiXuShuXingName)
				    
					//��ȡ���Ӧ��Classȫ·��������
				
				}
				
			} while (cursor.moveToNext());
			
		}
		
		return sqLiteTable;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		ArrayList<SQLiteTable> sqLiteTables = createTable(db);
		
		//���ﴴ����Ĺ����
		createTableManagerTable(db);
		for(SQLiteTable sqLiteTable:sqLiteTables){
			
			addTableToTableManger(db,sqLiteTable);
			
		}
	}

	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
