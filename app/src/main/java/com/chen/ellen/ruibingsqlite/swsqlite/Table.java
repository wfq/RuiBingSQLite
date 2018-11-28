package com.chen.ellen.ruibingsqlite.swsqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.util.List;

public interface Table<E>{

    void create(SQLiteDatabase db);
    void delete(SQLiteDatabase db);
    void addData(SQLiteDatabase db, E e);
    void addListData(SQLiteDatabase db, List<E> datas);
    boolean deleteData(SQLiteDatabase db, E e, Field field);
    boolean rename(SQLiteDatabase db, String name);
    boolean update(SQLiteDatabase db, E oldE, E newE, Field field);
    List<E> serach(SQLiteDatabase db, E e, Field field);
    List<E> setach(SQLiteDatabase db, E e, Field field, String condition);
    List<E> getAllData(SQLiteDatabase db);
}
