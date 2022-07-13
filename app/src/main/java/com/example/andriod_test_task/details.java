package com.example.andriod_test_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class details extends AppCompatActivity {

    TextView names;
    TextView address;
    TextView phone;
    TextView prices;


    String contentphone;
    String contentprice;
    String contentname;
    String contentaddress;
    String contentcurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Button btn = findViewById(R.id.backbtn);

        names=findViewById(R.id.name);
        contentname= getIntent().getStringExtra("Value1");
        names.setText(contentname);

        address=findViewById(R.id.adress);
        contentaddress=getIntent().getExtras().getString("Value2");
        address.setText(contentaddress);

        phone=findViewById(R.id.phone);
        contentphone=getIntent().getExtras().getString("Value3");
        phone.setText(contentphone);

        prices=findViewById(R.id.price);
        contentprice=getIntent().getExtras().getString("Value4");
        contentcurrency=getIntent().getExtras().getString("Value5");
        prices.setText(contentprice + contentcurrency);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

    }

    public void back(){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }
}