package com.example.online_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    EditText et_order_date, et_order_status;
    Button btn_add_value, btn_view_value;
    ListView lv_value;
    ArrayAdapter arrayAdapterOrder;
    DBOrder dbOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        et_order_date = findViewById(R.id.et_order_data);
        et_order_status = findViewById(R.id.et_status_data);
        btn_add_value = findViewById(R.id.btn_add_value);
        btn_view_value = findViewById(R.id.btn_all_value);
        lv_value = findViewById(R.id.lv_list_view);
        dbOrder = new DBOrder(MainActivity5.this);
        extracted(dbOrder);

        btn_add_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderModel orderModel;
                try {
                    String orderDate = et_order_date.getText().toString();
                    String orderStatus = et_order_status.getText().toString();

                    orderModel = new OrderModel(-1, orderDate, orderStatus);
                    Toast.makeText(MainActivity5.this, orderModel.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity5.this, "Error order", Toast.LENGTH_SHORT).show();
                    orderModel = new OrderModel(0, 1, 2, "error", "error");
                }

                DBOrder dbOrder = new DBOrder(MainActivity5.this);
                boolean success = dbOrder.addOne(orderModel);
                Toast.makeText(MainActivity5.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                extracted(dbOrder);

            }

        });

        btn_view_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBOrder dbOrder = new DBOrder(MainActivity5.this);
//                List<OrderModel> orderModelsEveryone = dbOrder.getEveryone();
                extracted(dbOrder);
//                Toast.makeText(MainActivity5.this, orderModelsEveryone.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv_value.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OrderModel orderModel = (OrderModel) adapterView.getItemAtPosition(i);
                dbOrder.deleteItem(orderModel);
                extracted(dbOrder);
                Toast.makeText(MainActivity5.this, "Deleted! " + orderModel, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void extracted(DBOrder dbOrder) {
        arrayAdapterOrder = new ArrayAdapter<OrderModel>(MainActivity5.this, android.R.layout.simple_list_item_1, dbOrder.getEveryone());
        lv_value.setAdapter(arrayAdapterOrder);
    }
}