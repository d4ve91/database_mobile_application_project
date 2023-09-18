package com.example.online_shop.package3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.online_shop.package3.ProviderModel;

import java.util.ArrayList;
import java.util.List;

public class DBProvider extends SQLiteOpenHelper {

    public static final String ID = "ID";
    public static final String PROVIDER_TABLE = "PROVER_TABLE";
    public static final String TEL_NUMBER = "TEL_NUMBER";
    public static final String ADDRESS = "ADDRESS";
    public static final String NAME = "NAME";

    public DBProvider(@Nullable Context context) {
        super(context, "provider.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + PROVIDER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + TEL_NUMBER + " TEXT, " + ADDRESS + " TEXT ) ";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(ProviderModel providerModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, providerModel.getName());
        contentValues.put(TEL_NUMBER, providerModel.getPhone_number());
        contentValues.put(ADDRESS, providerModel.getAddress());

        long insert = sqLiteDatabase.insert(PROVIDER_TABLE, null, contentValues);
        if (insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean deleteItem(ProviderModel providerModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PROVIDER_TABLE + " WHERE " + ID + " = " + providerModel.getID();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public List<ProviderModel> getEveryone(){

        List<ProviderModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + PROVIDER_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int providerID = cursor.getInt(0);
                String providerName = cursor.getString(1);
                String providerNum = cursor.getString(2);
                String providerAddress = cursor.getString(3);

                ProviderModel newProviderModel = new ProviderModel(providerID, providerName, providerNum, providerAddress);
                returnList.add(newProviderModel);

            }while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }
}
