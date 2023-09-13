package com.example.online_shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBCategory extends SQLiteOpenHelper {
    public static final String CATEGORY_TABLE = "CATEGORY_TABLE";
    public static final String COLUMN_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String ID = "ID";

    public DBCategory(@Nullable Context context) {
        super(context, "category.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + CATEGORY_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CATEGORY_NAME + " TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(CategoryModel categoryModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();

        contentValues.put(COLUMN_CATEGORY_NAME, categoryModel.getName());

        long insert = sqLiteDatabase.insert(CATEGORY_TABLE, null, contentValues);
        if (insert == - 1){
            return false;
        }else {
            return true;
        }
    }

    public boolean deleteItem(CategoryModel categoryModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CATEGORY_TABLE + " WHERE " + ID + " = " + categoryModel.getId();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }


    }

    public List<CategoryModel> getEveryone(){

        List<CategoryModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CATEGORY_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int categoryID = cursor.getInt(0);
                String categoryName = cursor.getString(1);

                CategoryModel newCategoryModel = new CategoryModel(categoryID, categoryName);
                returnList.add(newCategoryModel);
            }while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }
}
