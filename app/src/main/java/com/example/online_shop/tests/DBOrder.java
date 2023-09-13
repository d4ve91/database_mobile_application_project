//package com.example.online_shop;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DBOrder extends SQLiteOpenHelper {
//
//
//    public static final String ORDER_TABLE = "ORDER_TABLE";
//    public static final String ORDER_DATE = "ORDER_DATE";
//    public static final String ORDER_STATUS = "ORDER_STATUS";
//    public static final String ID = "ID";
//
//    public DBOrder(@Nullable Context context) {
//        super(context, "orders.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String crateOrderTable = "CREATE TABLE " + ORDER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORDER_DATE + " TEXT, " + ORDER_STATUS + " TEXT)";
//        sqLiteDatabase.execSQL(crateOrderTable);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//
//    public boolean addOne(OrderModel orderModel){
//
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(ORDER_DATE, orderModel.getOrderDate());
//        contentValues.put(ORDER_STATUS, orderModel.getOrderStatus());
//
//        long insert = sqLiteDatabase.insert(ORDER_TABLE, null, contentValues);
//        if (insert == -1){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//
////    public List<OrderModel> getEveryone(){
////        List<OrderModel> returnList = new ArrayList<>();
////
////        String queryString = "SELECT * FROM " + ORDER_TABLE;
////
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////
////        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
////
////        if (cursor.moveToFirst()){
////            do {
////                int orderID = cursor.getInt(0);
////                String orderDate = cursor.getString(1);
////                String orderStatus = cursor.getString(2);
////
////                OrderModel orderModel = new OrderModel(orderID, orderDate, orderStatus);
////                returnList.add(orderModel);
////
////            }while (cursor.moveToNext());
////        }
////        else {
////
////        }
////        cursor.close();
////        sqLiteDatabase.close();
////        return returnList;
////    }
//}
