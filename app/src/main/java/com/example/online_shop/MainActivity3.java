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

public class MainActivity3 extends AppCompatActivity {

    EditText editText_name, editText_number, editText_address;
    Button button_add, button_view;
    ListView listView_provider;
    ArrayAdapter arrayAdapter;
    DBProvider dbProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editText_name = findViewById(R.id.et_name);
        editText_number = findViewById(R.id.et_numb_tel);
        editText_address = findViewById(R.id.et_address);
        button_add = findViewById(R.id.button5);
        button_view = findViewById(R.id.button1);
        listView_provider = findViewById(R.id.lv_list_view);
        dbProvider = new DBProvider(MainActivity3.this);

        extracted(dbProvider);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProviderModel providerModel;
                try {
                    providerModel = new ProviderModel(-1, editText_name.getText().toString(), Long.parseLong(editText_number.getText().toString()), editText_address.getText().toString());
                    Toast.makeText(MainActivity3.this, providerModel.toString(), Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(MainActivity3.this, "Error", Toast.LENGTH_SHORT).show();
                    providerModel = new ProviderModel(-1, "error", 0, "error");
                }

                DBProvider dbProvider = new DBProvider(MainActivity3.this);
                boolean success = dbProvider.addOne(providerModel);
                Toast.makeText(MainActivity3.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                extracted(dbProvider);


            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBProvider dbProvider = new DBProvider(MainActivity3.this);
//                List <ProviderModel> providerModelList = dbProvider.getEveryone();
                extracted(dbProvider);
            }
        });

        listView_provider.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProviderModel providerModel = (ProviderModel) adapterView.getItemAtPosition(i);
                dbProvider.deleteItem(providerModel);
                extracted(dbProvider);
                Toast.makeText(MainActivity3.this, "Deleted! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void extracted(DBProvider dbProvider) {
        arrayAdapter = new ArrayAdapter<ProviderModel>(MainActivity3.this, android.R.layout.simple_list_item_1, dbProvider.getEveryone());
        listView_provider.setAdapter(arrayAdapter);
    }
}