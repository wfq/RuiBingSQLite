package com.chen.ellen.ruibingsqlite.swsqlite;

import java.util.List;

public interface SQLiteInterface {

    void open();
    void close();
    void createTable(RuiBingTable swsqLiteTable);
    void deleteTable(RuiBingTable swsqLiteTable);
    void reNameTable(RuiBingTable swsqLiteTable, String newName);
    <E> boolean saveData(RuiBingTable swsqLiteTable, E e);
    <E> boolean saveDatas(RuiBingTable SWSQLiteTable, List<E> datas);
    <E> boolean deleteData(RuiBingTable swsqLiteTable, E e, String name);
    <E> boolean deleteData(RuiBingTable swsqLiteTable, String deleteSQLString);
    <E> boolean upDateData(RuiBingTable swsqLiteTable, E odlE, E newE, String name);
    <E> List<E> serachData(RuiBingTable SWSQLiteTable, E e, String name);
    <E> List<E> serachDataFromMultiTable(List<RuiBingLibrary> swsqLiteLibraries, E e, String name);
    <E> List<E> serachData(RuiBingTable SWSQLiteTable, String serachSQLString);
    <E> List<E> getAllData(RuiBingTable SWSQLiteTable);
}
