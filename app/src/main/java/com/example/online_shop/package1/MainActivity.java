package com.example.online_shop.package1;

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

public class MainActivity extends AppCompatActivity {

    EditText et_productName, et_priceProduct;
    Button button_add, button_all;
    ListView listView_customers;

    ArrayAdapter adapter;
    DBProject dbProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_productName = findViewById(R.id.et_name);
        et_priceProduct = findViewById(R.id.et_price);
        button_add = findViewById(R.id.btn_add);
        button_all = findViewById(R.id.btn_All_Data);
        listView_customers = findViewById(R.id.lv_list_view);
        dbProject = new DBProject(MainActivity.this);
        extracted(dbProject);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductModel productModel;
                try {
                    productModel = new ProductModel(-1,et_productName.getText().toString(), Double.parseDouble(et_priceProduct.getText().toString()));
                    Toast.makeText(MainActivity.this, productModel.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Exception error product ! ", Toast.LENGTH_SHORT).show();
                    productModel = new ProductModel(-1, "error", 0);
                }

                DBProject dbProject = new DBProject(MainActivity.this);
                boolean success = dbProject.addOne(productModel);
                Toast.makeText(MainActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                extracted(dbProject);
            }
        });

        button_all.setOnClickListener((view) -> {
            DBProject dbProject = new DBProject(MainActivity.this);
//            List<ProductModel> everyone = dbProject.getEveryone();

            extracted(dbProject);

//            Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

        });

        listView_customers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductModel productModel = (ProductModel) adapterView.getItemAtPosition(i);
                dbProject.deleteItem(productModel);
                extracted(dbProject);
                Toast.makeText(MainActivity.this, "Deleted! " + productModel.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void extracted(DBProject dbProject2) {
        adapter = new ArrayAdapter<ProductModel>(MainActivity.this, android.R.layout.simple_list_item_1, dbProject2.getEveryone());
        listView_customers.setAdapter(adapter);
    }
}