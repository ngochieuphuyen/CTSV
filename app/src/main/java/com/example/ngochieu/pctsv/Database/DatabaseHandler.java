package com.example.ngochieu.pctsv.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by NgocHieu on 5/17/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "databaseManager";
    public static final String TABLE_MYDATA = "mydata";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    class MyDataColumns implements BaseColumns {
        static final String KEY_IDACCOUNT = "idAccount";
        static final String KEY_USERNAME = "username";
        static final String KEY_PASSWORD = "password";
        static final String KEY_EMAIL = "email";
        static final String KEY_ISLOGIN = "islogin";

    }

    private static final String CREATE_MYDATA_TABLE = "CREATE TABLE " + TABLE_MYDATA + "("
            + MyDataColumns._ID + " INTEGER PRIMARY KEY," + MyDataColumns.KEY_IDACCOUNT + " INTEGER,"
            + MyDataColumns.KEY_USERNAME + " TEXT,"  + MyDataColumns.KEY_PASSWORD + " TEXT,"
            + MyDataColumns.KEY_EMAIL + " TEXT,"  + MyDataColumns.KEY_ISLOGIN + " TEXT)";
    private static final String DROP_MYDATA_TABLE = "DROP TABLE IF EXISTS " + TABLE_MYDATA;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MYDATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_MYDATA_TABLE);
        onCreate(sqLiteDatabase);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
