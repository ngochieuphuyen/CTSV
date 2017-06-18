package com.example.ngochieu.pctsv.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ngochieu.pctsv.Model.MyAccount;


import static com.example.ngochieu.pctsv.Database.DatabaseHandler.TABLE_MYDATA;

/**
 * Created by NgocHieu on 5/17/2017.
 */

public class MyDataDAO {
    Context context;
    DatabaseHandler databaseHandler;

    public MyDataDAO(Context context) {
        this.context = context;
        databaseHandler = new DatabaseHandler(context);
    }
    public void addData(MyAccount myAccount){
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.MyDataColumns.KEY_IDACCOUNT,myAccount.getIdAccount());
        values.put(DatabaseHandler.MyDataColumns.KEY_USERNAME,myAccount.getUsername());
        values.put(DatabaseHandler.MyDataColumns.KEY_PASSWORD,myAccount.getPassword());
        values.put(DatabaseHandler.MyDataColumns.KEY_EMAIL,myAccount.getEmail());
        values.put(DatabaseHandler.MyDataColumns.KEY_ISLOGIN,"true");
        db.insert(TABLE_MYDATA, null, values);
        db.close();
    }
    public void deleteData(int idAccount) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TABLE_MYDATA, DatabaseHandler.MyDataColumns.KEY_IDACCOUNT + " = ?",
                new String[] { String.valueOf(idAccount) });
        db.close();
    }
    public MyAccount getAccount(){
        MyAccount myAccount = new MyAccount();
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        String sql =" select * from "+DatabaseHandler.TABLE_MYDATA;
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
                myAccount.setIdAccount(Integer.parseInt(cursor.getString(1)));
                myAccount.setUsername(cursor.getString(2));
                myAccount.setPassword(cursor.getString(3));
                myAccount.setEmail(cursor.getString(4));
                myAccount.setIsLogin(cursor.getString(5));
        }
        return myAccount;
    }
}
