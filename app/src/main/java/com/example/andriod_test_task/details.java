package com.example.andriod_test_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class details extends AppCompatActivity {

    TextView names;
    TextView address;
    TextView phone;
    TextView prices;
    String id;
    private static HttpURLConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Button btn = findViewById(R.id.backbtn);

        names=findViewById(R.id.name);
       address=findViewById(R.id.adress);
        phone=findViewById(R.id.phone);
        prices=findViewById(R.id.price);


        id= getIntent().getStringExtra("Value1");

        new ConnectionJson().execute(id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

    }

   public class ConnectionJson extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... strings) {
            String urlid=strings[0];
            BufferedReader readers;
            StringBuffer responseContent = new StringBuffer();


            try {
                String line;
                URL url = new URL("https://engine.free.beeceptor.com/api/getSportDetails?sportId="+urlid);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                readers = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = readers.readLine()) != null){

                    responseContent.append(line);
                }

                String content = responseContent.toString();

                JSONObject jsonObject =new JSONObject(content);


                String name = jsonObject.getString("name");
                String adress = jsonObject.getString("address");
                String phones = jsonObject.getString("phone");
                try{
                    String price = jsonObject.getString("price");
                    String currency = jsonObject.getString("currency");
                    prices.setText(price + currency);
                }catch (JSONException e){
                    e.printStackTrace();
                }


                names.setText(name);
                address.setText(adress);
                phone.setText(phones);;



                return null;

            } catch (IOException e) {
                connection.disconnect();
            }catch(JSONException e){
                connection.disconnect();
            }
            finally {
                connection.disconnect();
            }

            return null;
        }


    }
    public void back(){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }
}