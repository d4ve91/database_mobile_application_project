package com.example.online_shop.package4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.online_shop.R;

public class MainActivity4 extends AppCompatActivity {

    EditText et_category, et_producer, et_date;
    Button add_data, all_view_data;
    ListView lv_details;
    ArrayAdapter arrayAdapter;
    DBDetailsProduct dbDetailsProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        et_category = findViewById(R.id.et_name_category_producer);
        et_producer = findViewById(R.id.et_name_producer);
        et_date = findViewById(R.id.et_added_date);
        add_data = findViewById(R.id.btn_add_value);
        all_view_data = findViewById(R.id.btn_all_value);
        lv_details = findViewById(R.id.lv_list_view);

        dbDetailsProduct = new DBDetailsProduct(MainActivity4.this);

        extracted(dbDetailsProduct);

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsProductModel detailsProductModel = new DetailsProductModel(-1, et_category.getText().toString(), et_producer.getText().toString(), et_date.getText().toString());
                Toast.makeText(MainActivity4.this, detailsProductModel.toString(), Toast.LENGTH_SHORT).show();

                DBDetailsProduct dbDetailsProduct = new DBDetailsProduct(MainActivity4.this);
                boolean success = dbDetailsProduct.addOne(detailsProductModel);
                Toast.makeText(MainActivity4.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                extracted(dbDetailsProduct);
            }
        });

        all_view_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBDetailsProduct dbDetailsProduct = new DBDetailsProduct(MainActivity4.this);
//                List<DetailsProductModel> detailsEveryone = dbDetailsProduct.getEveryone();

                extracted(dbDetailsProduct);
//                Toast.makeText(MainActivity4.this, detailsEveryone.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv_details.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DetailsProductModel detailsProduct = (DetailsProductModel) adapterView.getItemAtPosition(i);
                dbDetailsProduct.deleteItem(detailsProduct);
                extracted(dbDetailsProduct);
                Toast.makeText(MainActivity4.this, "Deleted! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void extracted(DBDetailsProduct dbDetailsProduct) {
        arrayAdapter = new ArrayAdapter<DetailsProductModel>(MainActivity4.this, android.R.layout.simple_list_item_1, dbDetailsProduct.getEveryone());
        lv_details.setAdapter(arrayAdapter);
    }
}