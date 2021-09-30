package com.example.sqlite_basic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlite_basic.Models.User;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Image.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE "+ DBEntry.GroceryEntry.TABLE_NAME + " (" +
                DBEntry.GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBEntry.GroceryEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                DBEntry.GroceryEntry.COLUMN_PASSWORD + " INTEGER NOT NULL," +
                DBEntry.GroceryEntry.COLUMN_IMAGE + " BLOB NOT NULL " +
                ");";


        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists " + DBEntry.GroceryEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertDetails(String email,String password,byte[] Image){

        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBEntry.GroceryEntry.COLUMN_EMAIL,email);
        contentValues.put(DBEntry.GroceryEntry.COLUMN_PASSWORD,password);
        contentValues.put(DBEntry.GroceryEntry.COLUMN_IMAGE,Image);
        long results = DB.insert(DBEntry.GroceryEntry.TABLE_NAME,null,contentValues);
        if(results == -1){
            return false;
        }
        else {
            return true;
        }
    }


    public Boolean UpdteDetails(String email,String password){

        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBEntry.GroceryEntry.COLUMN_EMAIL,email);
        contentValues.put(DBEntry.GroceryEntry.COLUMN_PASSWORD,password);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where _ID =?",new String[]{DBEntry.GroceryEntry._ID});
        if (cursor.getCount() > 0) {
            long results = DB.update("Userdetails", contentValues, "id=?", new String[]{});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }


    public Boolean DeleteDetails(){

        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where id =?",new String[]{DBEntry.GroceryEntry._ID});
        if (cursor.getCount() > 0) {
            long results = DB.delete("Userdetails", "id=?", new String[]{});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor ViewDetails(){

        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
    }



    public Boolean insertModelData(User user){

        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",user.getId());
        contentValues.put("Email",user.getEmail());
        contentValues.put("Password",user.getPassword());
        long results = DB.insert("Userdetails",null,contentValues);
        if(results == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public List <User> ViewModelDetails(){

        SQLiteDatabase DB = this.getReadableDatabase();
       List <User> user = (List<User>) DB.rawQuery("Select * from Userdetails",null);
        return user;
    }
}
