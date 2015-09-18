package com.choimeetsworld.cursoradapter.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hychoi on 9/6/2015.
 * Object for creating and keeping the database object
 */

public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper sInstance;

    public static final String DATABASE_NAME = "orders_database.db";
    public static final String TABLE_NAME = "order_list";
    public static final int DATABASE_VERSION = 1;
    public static final String COL_ID = "_id";
    public static final String COL_ORDER = "order_name";
    public static final String COL_CUSTOMER = "customer_name";
    public static final String COL_ADDRESS = "customer_address";
    public static final String COL_NUMBER = "customer_number";
    public static final String COL_PRICE = "order_price";
    public static final String COL_TIME = "order_time";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            "_id integer primary key autoincrement, " +
            COL_ORDER + " text not null, " +
            COL_ADDRESS + " text not null, " +
            COL_NUMBER + " text not null, " +
            COL_PRICE + " text not null, " +
            COL_TIME + " text not null, " +
            COL_CUSTOMER + " text not null); ";

    public static synchronized DbHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
