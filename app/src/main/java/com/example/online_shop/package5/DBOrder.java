package com.example.online_shop.package5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBOrder extends SQLiteOpenHelper {

    public static final String ID = "ID";
    public static final String ID_ORDERS = "ID_ORDERS";
    public static final String ID_CUSTOMERS = "ID_CUSTOMERS";
    public static final String ORDER_TABLE = "ORDER_TABLE";
    public static final String ORDER_DATE = "ORDER_DATE";
    public static final String ORDER_STATUS = "ORDER_STATUS";
    private OrderModel orderModel;

    public DBOrder(@Nullable Context context) {
        super(context, "orders.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dataBaseTable = "CREATE TABLE " + ORDER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ID_ORDERS + " INT, " + ID_CUSTOMERS + " INT, " + ORDER_DATE + " TEXT, " + ORDER_STATUS + " TEXT)";
        sqLiteDatabase.execSQL(dataBaseTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(OrderModel orderModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_ORDERS, orderModel.getID_Orders());
        contentValues.put(ID_CUSTOMERS, orderModel.getID_Customers());
        contentValues.put(ORDER_DATE, orderModel.getOrderDate());
        contentValues.put(ORDER_STATUS, orderModel.getOrderStatus());
//        contentValues.put(ID, productModel.getID());
        long insert = sqLiteDatabase.insert(ORDER_TABLE, null, contentValues);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteItem(OrderModel orderModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ORDER_TABLE + " WHERE " + ID + " = " + orderModel.getID();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

//    public boolean deleteItem(OrderModel orderModel){
//
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String queryString = "DELETE FROM " + ORDER_TABLE + " WHERE " + ID + " = " + orderModel.getID();
//        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
//        if (cursor.moveToFirst()){
//            return true;
//        }else {
//            return false;
//        }
//    }

    public List<OrderModel> getEveryone() {
        List<OrderModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ORDER_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int orderID = cursor.getInt(0);
                    int ordersID = cursor.getInt(1);
                    int customersID = cursor.getInt(2);
                    String orderDate = cursor.getString(3);
                    String orderStatus = cursor.getString(4);

                    OrderModel newOrder = new OrderModel(orderID, ordersID, customersID, orderDate, orderStatus);

                    // int idOrders = cursor.getInt(cursor.getColumnIndex("ID_Orders"));
                    // int idCustomers = cursor.getInt(cursor.getColumnIndex("ID_Customers"));
                    // newOrder.setID_Orders(idOrders);
                    // newOrder.setID_Customers(idCustomers);

                    returnList.add(newOrder);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            sqLiteDatabase.close();
        }

        return returnList;
    }


}
