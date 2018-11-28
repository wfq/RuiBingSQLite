package com.chen.ellen.ruibingsqlite.ruibingsqlite;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class RuiBingLibrary implements Library{

    private String libraryName;
    private List<RuiBingTable> swsqLiteTables;

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public List<RuiBingTable> getSwsqLiteTables() {
        return swsqLiteTables;
    }

    public void setSwsqLiteTables(List<RuiBingTable> swsqLiteTables) {
        this.swsqLiteTables = swsqLiteTables;
    }

    public RuiBingLibrary(String libraryName){
        this.libraryName = libraryName;
    }

    @Override
    public void create(SQLiteDatabase db) {

    }

    @Override
    public void delete(SQLiteDatabase db) {

    }

    @Override
    public void rename(SQLiteDatabase db, String name) {

    }

    @Override
    public void serach(SQLiteDatabase db, String name) {

    }

    @Override
    public void open(SQLiteDatabase db) {

    }

    @Override
    public void close(SQLiteDatabase db) {

    }
}
