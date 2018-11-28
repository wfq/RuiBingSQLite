package com.chen.ellen.ruibingsqlite.ruibingsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RuiBingTable<E> implements Table<E> {

    private String tableName = "";
    private Class tableClass;
    private RuiBingLibrary swsqLiteLibrary;
    private Field keyField;
    private Field conditionField;

    public Field getConditionField() {
        return conditionField;
    }

    public void setConditionField(Field conditionField) {
        this.conditionField = conditionField;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public RuiBingLibrary getSwsqLiteLibrary() {
        return swsqLiteLibrary;
    }

    public void setSwsqLiteLibrary(RuiBingLibrary swsqLiteLibrary) {
        this.swsqLiteLibrary = swsqLiteLibrary;
    }

    public Field getKeyField() {
        return keyField;
    }

    public void setKeyField(Field keyField) {
        this.keyField = keyField;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName, RuiBingLibrary swsqLiteLibrary) {
        this.tableName = tableName;
        this.swsqLiteLibrary = swsqLiteLibrary;
    }

    public Class getTableClass() {
        return tableClass;
    }

    public void setTableClass(Class tableClass) {
        this.tableClass = tableClass;
    }

    public RuiBingTable(String tableName, Field keyField, Class tableClass) {
        this.tableName = tableName;
        this.tableClass = tableClass;
        this.keyField = keyField;
        this.conditionField = keyField;
    }

    public RuiBingTable(String tableName, Class tableClass) {
        this.tableName = tableName;
        this.tableClass = tableClass;
    }

    @Override
    public void create(SQLiteDatabase db) {
        Field[] fields = tableClass.getDeclaredFields();
        String sqlString = "";
        for (Field field : fields) {
            field.setAccessible(true);
            String ziDuanName = field.getName();
            String ziDuanType = getSQlStringType(field.getType());
            if (!ziDuanType.equals("no")) {
                if (ziDuanName.equals(this.getKeyField() == null ? "" : this.getKeyField().getName())) {
                    if (!ziDuanType.equals("integer")) {
                    } else {
                        sqlString = sqlString + ziDuanName + " " + ziDuanType + " primary key autoincrement,";
                    }
                } else {
                    sqlString = sqlString + ziDuanName + " " + ziDuanType + ",";
                }
            }
        }
        sqlString = sqlString.substring(0, sqlString.length() - 1);
        sqlString = "create table " + this.getTableName() + "(" + sqlString + ")";
        try {
            db.execSQL(sqlString);
        } catch (SQLiteException e) {
            // TODO: handle exception
        }
    }

    @Override
    public void delete(SQLiteDatabase db) {

    }

    @Override
    public void addData(SQLiteDatabase db, E e) {
        ContentValues contentValues = new ContentValues();
        Field[] fields = tableClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!getSQlStringType(field.getType()).equals("no")) {
                try {
                    if (field.getType() == Byte.class || field.getType().getName().equals("byte")) {
                        contentValues.put(field.getName(), field.getByte(e));
                    } else if (field.getType() == Short.class || field.getType().getName().equals("short")) {
                        contentValues.put(field.getName(), field.getShort(e));
                    } else if (field.getType() == Integer.class || field.getType().getName().equals("int")) {
                        contentValues.put(field.getName(), field.getInt(e));
                    } else if (field.getType() == Long.class || field.getType().getName().equals("long")) {
                        contentValues.put(field.getName(), field.getLong(e));
                    } else if (field.getType() == Float.class || field.getType().getName().equals("float")) {
                        contentValues.put(field.getName(), field.getFloat(e));
                    } else if (field.getType() == Double.class || field.getType().getName().equals("double")) {
                        contentValues.put(field.getName(), field.getDouble(e));
                    } else if (field.getType() == Character.class || field.getType().getName().equals("char")) {
                        contentValues.put(field.getName(), field.getChar(e) + "");
                    } else if (field.getType() == Boolean.class || field.getType().getName().equals("boolean")) {
                        contentValues.put(field.getName(), field.getBoolean(e) ? 1 : 0);
                    } else if (field.getType() == String.class) {
                        contentValues.put(field.getName(), (String) field.get(e));
                    }
                    if (field.getName().equals(this.getKeyField() == null ? "" : this.getKeyField().getName())) {
                        contentValues.remove(field.getName());
                    }
                } catch (IllegalAccessException ee) {
                    // TODO Auto-generated catch block
                    ee.printStackTrace();
                } catch (IllegalArgumentException ee) {
                    // TODO Auto-generated catch block
                    ee.printStackTrace();
                }

            }
        }
        db.insert(tableName, null, contentValues);
        contentValues.clear();
        db.close();
    }

    @Override
    public void addListData(SQLiteDatabase db, List<E> datas) {
        for(int i=0;i<datas.size();i++){
            addData(db,datas.get(i));
        }
    }

    @Override
    public boolean deleteData(SQLiteDatabase db, E e,Field targetField) {
        String value = null;
        targetField.setAccessible(true);
        try {
            if (targetField.getType() == Byte.class || targetField.getType().getName().equals("byte")) {
                value = targetField.getByte(e) + "";
            } else if (targetField.getType() == Short.class || targetField.getType().getName().equals("short")) {
                value = targetField.getShort(e) + "";
            } else if (targetField.getType() == Integer.class || targetField.getType().getName().equals("int")) {
                value = targetField.getInt(e) + "";
            } else if (targetField.getType() == Long.class || targetField.getType().getName().equals("long")) {
                value = targetField.getLong(e) + "";
            } else if (targetField.getType() == Float.class || targetField.getType().getName().equals("float")) {
                value = targetField.getFloat(e) + "";
            } else if (targetField.getType() == Double.class || targetField.getType().getName().equals("double")) {
                value = targetField.getDouble(e) + "";
            } else if (targetField.getType() == Character.class || targetField.getType().getName().equals("char")) {
                value = targetField.getChar(e) + "";
            } else if (targetField.getType() == Boolean.class || targetField.getType().getName().equals("boolean")) {
                value = targetField.getBoolean(e) ? 1 + "" : 0 + "";
            } else if (targetField.getType() == String.class) {
                value = (String) targetField.get(e);
            }
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        String[] args = new String[1];
        args[0] = value;
        String deleteSQLString =  targetField.getName() + " = ?";
        db.delete(this.getTableName(),deleteSQLString,args);
        db.close();
        return true;
    }

    @Override
    public boolean rename(SQLiteDatabase db, String name) {
        return false;
    }

    @Override
    public boolean update(SQLiteDatabase db, E oldE,E newE, Field targetField) {
        String value = null;
        targetField.setAccessible(true);
        List<E> dataList = new ArrayList<>();
        try {
            if (targetField.getType() == Byte.class || targetField.getType().getName().equals("byte")) {
                value = targetField.getByte(oldE) + "";
            } else if (targetField.getType() == Short.class || targetField.getType().getName().equals("short")) {
                value = targetField.getShort(oldE) + "";
            } else if (targetField.getType() == Integer.class || targetField.getType().getName().equals("int")) {
                value = targetField.getInt(oldE) + "";
            } else if (targetField.getType() == Long.class || targetField.getType().getName().equals("long")) {
                value = targetField.getLong(oldE) + "";
            } else if (targetField.getType() == Float.class || targetField.getType().getName().equals("float")) {
                value = targetField.getFloat(oldE) + "";
            } else if (targetField.getType() == Double.class || targetField.getType().getName().equals("double")) {
                value = targetField.getDouble(oldE) + "";
            } else if (targetField.getType() == Character.class || targetField.getType().getName().equals("char")) {
                value = targetField.getChar(oldE) + "";
            } else if (targetField.getType() == Boolean.class || targetField.getType().getName().equals("boolean")) {
                value = targetField.getBoolean(oldE) ? 1 + "" : 0 + "";
            } else if (targetField.getType() == String.class) {
                value = (String) targetField.get(oldE);
            }
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        String[] args = new String[1];
        args[0] = value;
        ContentValues contentValues = new ContentValues();
        String upDateSQLString =  targetField.getName() + " = ?";
        Field[] fields = tableClass.getDeclaredFields();
        for(Field field:fields){
            try {
                field.setAccessible(true);
                if (field.getType() == Byte.class || field.getType().getName().equals("byte")) {
                   contentValues.put(field.getName(),field.getByte(newE));
                } else if (field.getType() == Short.class || field.getType().getName().equals("short")) {
                    contentValues.put(field.getName(),field.getShort(newE));
                } else if (field.getType() == Integer.class || field.getType().getName().equals("int")) {
                    contentValues.put(field.getName(),field.getInt(newE));
                } else if (field.getType() == Long.class || field.getType().getName().equals("long")) {
                    contentValues.put(field.getName(),field.getLong(newE));
                } else if (field.getType() == Float.class || field.getType().getName().equals("float")) {
                    contentValues.put(field.getName(),field.getFloat(newE));
                } else if (field.getType() == Double.class || field.getType().getName().equals("double")) {
                    contentValues.put(field.getName(),field.getDouble(newE));
                } else if (field.getType() == Character.class || field.getType().getName().equals("char")) {
                    contentValues.put(field.getName(),field.getChar(newE)+"");
                } else if (field.getType() == Boolean.class || field.getType().getName().equals("boolean")) {
                    contentValues.put(field.getName(),field.getBoolean(newE)?1:0);
                } else if (field.getType() == String.class) {
                    contentValues.put(field.getName(), (String) field.get(newE));
                }
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }
        db.update(this.getTableName(),contentValues,upDateSQLString,args);
        db.close();
        return true;
    }

    @Override
    public List<E> serach(SQLiteDatabase db, E e, Field targetField){
        String value = null;
        targetField.setAccessible(true);
        List<E> dataList = new ArrayList<>();
        try {
            if (targetField.getType() == Byte.class || targetField.getType().getName().equals("byte")) {
                value = targetField.getByte(e) + "";
            } else if (targetField.getType() == Short.class || targetField.getType().getName().equals("short")) {
                value = targetField.getShort(e) + "";
            } else if (targetField.getType() == Integer.class || targetField.getType().getName().equals("int")) {
                value = targetField.getInt(e) + "";
            } else if (targetField.getType() == Long.class || targetField.getType().getName().equals("long")) {
                value = targetField.getLong(e) + "";
            } else if (targetField.getType() == Float.class || targetField.getType().getName().equals("float")) {
                value = targetField.getFloat(e) + "";
            } else if (targetField.getType() == Double.class || targetField.getType().getName().equals("double")) {
                value = targetField.getDouble(e) + "";
            } else if (targetField.getType() == Character.class || targetField.getType().getName().equals("char")) {
                value = targetField.getChar(e) + "";
            } else if (targetField.getType() == Boolean.class || targetField.getType().getName().equals("boolean")) {
                value = targetField.getBoolean(e) ? 1 + "" : 0 + "";
            } else if (targetField.getType() == String.class) {
                value = (String) targetField.get(e);
            }
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        String[] args = new String[1];
        args[0] = value;
        Field[] fields = tableClass.getDeclaredFields();
        String serachSQLString = "SELECT * FROM" + " " + this.tableName + " " + "WHERE " + targetField.getName() + " = ?";
        Cursor cursor = db.rawQuery(serachSQLString, args);
        Constructor con1 = null;
        try {
            con1 = tableClass.getConstructor();
            if (cursor.moveToFirst()) {
                do {
                    Object obj = con1.newInstance();
                    for (Field field : fields) {
                        field.setAccessible(true);
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
                    dataList.add((E) obj);
                } while (cursor.moveToNext());

            }
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        db.close();
        return dataList;
    }

    @Override
    public List<E> setach(SQLiteDatabase db, E e, Field field, String condition) {
        //SELECT tbl_name FROM sqlite_master WHERE type = 'table';
        return null;
    }

    @Override
    public List<E> getAllData(SQLiteDatabase db) {
        ArrayList<E> dataList = new ArrayList<>();
        Field[] fields = tableClass.getDeclaredFields();
        Cursor cursor = db.query(getTableName(), null, null, null, null, null,
                null);
        Constructor<?> con1;
        try {
            con1 = tableClass.getConstructor();

            if (cursor.moveToFirst()) {

                do {
                    Object obj = con1.newInstance();
                    for (Field field : fields) {
                        field.setAccessible(true);
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
                    dataList.add((E) obj);
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
        db.close();
        return dataList;
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

}
