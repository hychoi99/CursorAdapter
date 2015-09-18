package com.choimeetsworld.cursoradapter.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by hychoi on 9/6/2015.
 */
public class DataAccessObject {

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private static final String TAG = "DataAccessObject";


    public DataAccessObject(Context context) {
        dbHelper = DbHelper.getInstance(context);
    }

    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        db.close();
    }

    public void createRow(String customer, String order,
                          String address, String phone, String price, String time) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_CUSTOMER, customer);
        values.put(dbHelper.COL_ORDER, order);
        values.put(dbHelper.COL_ADDRESS, address);
        values.put(dbHelper.COL_NUMBER, phone);
        values.put(dbHelper.COL_PRICE, price);
        values.put(dbHelper.COL_TIME, time);

        db.insert(dbHelper.TABLE_NAME, null, values);
        Log.i(TAG, "Inserted row with customer name = " + customer);
    }

    public void deleteRow(String id) {
        System.out.println("123" + id);
        System.out.println("222" + dbHelper.COL_ID);
        db.delete(dbHelper.TABLE_NAME, dbHelper.COL_ID + " = " + id, null);
        Log.i(TAG, "Deleted row with row id = " + id);
    }

    public void updateRow(String id, String customer, String order,
                          String address, String phone, String price, String time) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_CUSTOMER, customer);
        values.put(dbHelper.COL_ORDER, order);
        values.put(dbHelper.COL_ADDRESS, address);
        values.put(dbHelper.COL_NUMBER, phone);
        values.put(dbHelper.COL_PRICE, price);
        values.put(dbHelper.COL_TIME, time);

        db.update(dbHelper.TABLE_NAME, values, dbHelper.COL_ID + " = " + id, null);
        Log.i(TAG, "Updated row with row id = " + id);
    }

    public Cursor getItem(String id) {
        Cursor cursor = db.query(dbHelper.TABLE_NAME, null, dbHelper.COL_ID
                + " = " + id, null, null, null, null);
        return cursor;
    }

    public Cursor getAll() {
        return db.query(dbHelper.TABLE_NAME, null, null, null, null, null, null);
    }

    public void clearDb() {
        db.execSQL("delete from "+ dbHelper.TABLE_NAME);
        Log.i(TAG, "Cleared table");
        dbHelper.onUpgrade(db, 1, 1);
    }
}
