package com.chen.ellen.ruibingsqlite.swsqlite;

import android.database.sqlite.SQLiteDatabase;

public interface Library {

    void create(SQLiteDatabase db);
    void delete(SQLiteDatabase db);
    void rename(SQLiteDatabase db, String name);
    void serach(SQLiteDatabase db, String name);
    void open(SQLiteDatabase db);
    void close(SQLiteDatabase db);
}
