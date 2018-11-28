package com.chen.ellen.ruibingsqlite.swsqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.List;

public class RuiBingSQLite extends SQLiteOpenHelper implements SQLiteInterface {

    private RuiBingLibrary swsqLiteLibrary;

    public RuiBingSQLite(Context context, RuiBingLibrary swsqLiteLibrary, int version) {
        super(context, swsqLiteLibrary.getLibraryName(), null, version);
    }

    private RuiBingSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        swsqLiteLibrary = new RuiBingLibrary(name);
    }

    private RuiBingSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        swsqLiteLibrary = new RuiBingLibrary(name);
    }

    private RuiBingSQLite(@Nullable Context context, @Nullable String name, int version, @Nullable SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
        swsqLiteLibrary = new RuiBingLibrary(name);
    }

    public RuiBingLibrary getSwsqLiteLibrary() {
        return swsqLiteLibrary;
    }

    public void setSwsqLiteLibrary(RuiBingLibrary swsqLiteLibrary) {
        this.swsqLiteLibrary = swsqLiteLibrary;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void open() {
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void createTable(RuiBingTable swsqLiteTable) {
        swsqLiteTable.create(this.getWritableDatabase());
    }

    @Override
    public void deleteTable(RuiBingTable swsqLiteTable) {

    }

    @Override
    public void reNameTable(RuiBingTable swsqLiteTable, String newName) {

    }

    @Override
    public <E> boolean saveData(RuiBingTable swsqLiteTable, E e) {
        swsqLiteTable.addData(this.getWritableDatabase(), e);
        return false;
    }

    @Override
    public <E> boolean saveDatas(RuiBingTable SWSQLiteTable, List<E> datas) {
        return false;
    }

    @Override
    public <E> boolean deleteData(RuiBingTable swsqLiteTable, E e, String name) {
        Field targetField = null;
        Class tabCalss = e.getClass();
        Field[] fields = tabCalss.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                targetField = field;
                break;
            }
        }
        return  swsqLiteTable.deleteData(this.getWritableDatabase(), e, targetField);
    }

    @Override
    public <E> boolean deleteData(RuiBingTable swsqLiteTable, String deleteSQLString) {
        return false;
    }

    @Override
    public <E> boolean upDateData(RuiBingTable swsqLiteTable, E oldE, E newE, String name) {
        Field targetField = null;
        Class tabCalss = oldE.getClass();
        Field[] fields = tabCalss.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                targetField = field;
                break;
            }
        }
        return swsqLiteTable.update(this.getWritableDatabase(),oldE,newE,targetField);
    }

    @Override
    public <E> List<E> serachData(RuiBingTable swsqLiteTable, E e, String name) {
        List<E> dataList ;
        Field targetField = null;
        Class tabCalss = e.getClass();
        Field[] fields = tabCalss.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                targetField = field;
                break;
            }
        }
        dataList = swsqLiteTable.serach(this.getWritableDatabase(), e, targetField);
        return dataList;
    }

    @Override
    public <E> List<E> serachDataFromMultiTable(List<RuiBingLibrary> swsqLiteLibraries, E e, String name) {
        //思路1，就是遍历表，然后将每个表查到的数据进行整合，但是效率肯定特别低
        return null;
    }

    @Override
    public <E> List<E> serachData(RuiBingTable SWSQLiteTable, String serachSQLString) {
        return null;
    }

    @Override
    public <E> List<E> getAllData(RuiBingTable SWSQLiteTable) {
        return SWSQLiteTable.getAllData(this.getWritableDatabase());
    }
}
