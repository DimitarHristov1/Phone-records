package com.a13118059;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.a13118059.DataModel.Item_Database;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Declare the variables that are needed and initialize them
    public static final String DATABASE_NAME = "Contacts.db";
    public static final String TABLE_NAME = "contact_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ONE = "NAME";
    public static final String COLUMN_TWO = "PHONE";

    //Create the method for the "DatabaseHelper", with which we can declare variables from type "DatabaseHelper" to access the methods from this class
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //Create the method for the database creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ONE + " TEXT, " + COLUMN_TWO + " TEXT" + ")");
    }

    //Create the method if the table from the database already exist to be deleted before proceeding
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //Create the method for inserting data into the table
    public boolean insertData(String name,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ONE,name);
        contentValues.put(COLUMN_TWO,phone);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        return result != -1;
    }

    //Create the method for deleting the data from table
    public Integer deleteData (int contact_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {String.valueOf(contact_id)});
    }

    //Create the method with which we can show the data from the table
    public ArrayList<Item_Database> getAllData(){
        ArrayList<Item_Database> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        //Move the cursor through the table rows, so we can store them temporarily in the "Item_Database" array
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            Item_Database contact = new Item_Database(id, name, phone, false);
            dataList.add(contact);
        }
        return dataList;
    }
}

