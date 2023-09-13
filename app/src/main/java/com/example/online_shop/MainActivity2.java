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

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    Button btn_add, btn_all;
    EditText et_name_category;
    ListView lv_category;
    ArrayAdapter customArrayAdapter;
    DBCategory dbCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_add = findViewById(R.id.btn_add);
        btn_all= findViewById(R.id.btn_add2);
        et_name_category = findViewById(R.id.et_name);
        lv_category = findViewById(R.id.lv_list_view);
        dbCategory = new DBCategory(MainActivity2.this);

        extracted(dbCategory);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryModel categoryModel;
                try {
                    categoryModel = new CategoryModel(-1, et_name_category.getText().toString());
                    Toast.makeText(MainActivity2.this, categoryModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity2.this, "Exception error category", Toast.LENGTH_SHORT).show();
                    categoryModel = new CategoryModel(-1, "error");
                }

                DBCategory dbCategory = new DBCategory(MainActivity2.this);
                boolean success = dbCategory.addOne(categoryModel);
                Toast.makeText(MainActivity2.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                extracted(dbCategory);


            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBCategory dbCategory = new DBCategory(MainActivity2.this);
//                List<CategoryModel> everyone = dbCategory.getEveryone();
                extracted(dbCategory);
//                Toast.makeText(MainActivity2.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CategoryModel categoryModel = (CategoryModel) adapterView.getItemAtPosition(i);
                dbCategory.deleteItem(categoryModel);
                extracted(dbCategory);
                Toast.makeText(MainActivity2.this, "Deleted! " + categoryModel.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void extracted(DBCategory dbCategory) {
        customArrayAdapter = new ArrayAdapter<CategoryModel>(MainActivity2.this, android.R.layout.simple_list_item_1, dbCategory.getEveryone());
        lv_category.setAdapter(customArrayAdapter);
    }
}