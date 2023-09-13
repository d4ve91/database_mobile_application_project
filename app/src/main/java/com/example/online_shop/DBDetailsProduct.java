package com.example.online_shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBDetailsProduct extends SQLiteOpenHelper {

    public static final String ID = "ID";
    public static final String DETAILS_TABLE = "DETAILS_TABLE";
    public static final String CATEGORY = "CATEGORY";
    public static final String PRODUCER = "PRODUCER";
    public static final String ADD_DATE = "ADD_DATE";

    public DBDetailsProduct(@Nullable Context context) {
        super(context, "details.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + DETAILS_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY + " TEXT, " + PRODUCER + " TEXT, " + ADD_DATE + " TEXT ) ";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(DetailsProductModel detailsProductModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CATEGORY, detailsProductModel.getCategory());
        contentValues.put(PRODUCER, detailsProductModel.getProducer());
        contentValues.put(ADD_DATE, detailsProductModel.getAdded_date());

        long insert = sqLiteDatabase.insert(DETAILS_TABLE, null, contentValues);
        if (insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean deleteItem(DetailsProductModel detailsProductModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + DETAILS_TABLE + " WHERE " + ID + " = " + detailsProductModel.getID();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public List<DetailsProductModel> getEveryone(){

        List<DetailsProductModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + DETAILS_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int detailsID = cursor.getInt(0);
                String categoryName = cursor.getString(1);
                String producerName = cursor.getString(2);
                String add_date = cursor.getString(3);

                DetailsProductModel newDetails = new DetailsProductModel(detailsID, categoryName, producerName, add_date);
                returnList.add(newDetails);

            }while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }
}




