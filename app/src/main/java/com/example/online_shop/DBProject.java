package com.example.online_shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBProject extends SQLiteOpenHelper {

    public static final String ID = "ID";
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    public DBProject(@Nullable Context context) {
        super(context, "products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dataBaseTables = "CREATE TABLE " + PRODUCT_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_PRICE + " DOUBLE)";
        sqLiteDatabase.execSQL(dataBaseTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(ProductModel productModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_PRODUCT_NAME, productModel.getProductName());
        contentValues.put(COLUMN_PRODUCT_PRICE, productModel.getPriceProduct());
//        contentValues.put(ID, productModel.getID());
        long insert = sqLiteDatabase.insert(PRODUCT_TABLE, null, contentValues);
         if (insert == -1){
             return false;
         }
         else {
             return true;
         }
    }

    public boolean deleteItem(ProductModel productModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PRODUCT_TABLE + " WHERE " + ID + " = " + productModel.getID();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }
    public List<ProductModel> getEveryone(){
        List<ProductModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + PRODUCT_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int productID = cursor.getInt(0);
                String productName = cursor.getString(1);
                double priceProduct = cursor.getDouble(2);

                ProductModel newProductModel = new ProductModel(productID, productName, priceProduct);
                returnList.add(newProductModel);

            }while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }
}
