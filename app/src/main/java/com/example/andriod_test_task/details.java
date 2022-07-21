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
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;


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

        Button BtnBack = findViewById(R.id.backbtn);

         names = findViewById(R.id.name);
         address = findViewById(R.id.adress);
         phone = findViewById(R.id.phone);
         prices = findViewById(R.id.price);
         id= getIntent().getStringExtra("id");

        new ConnectionJson().execute(id);

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

   public class ConnectionJson extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... id) {
            TreeSet<String> tree = new TreeSet<String>(Arrays.asList(id));
            String urlid= tree.first();
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
                HashMap<String, Object> map = new HashMap<String, Object>();

                map.put("name",jsonObject.getString("name"));
                map.put("adress",jsonObject.getString("address"));
                map.put("phone",jsonObject.getString("phone"));

                String name = String.valueOf(map.get("name"));
                String adress = String.valueOf(map.get("adress"));
                String phones = String.valueOf(map.get("phone"));
                try{
                    map.put("price",jsonObject.getString("price"));
                    map.put("currency",jsonObject.getString("currency"));
                    String price = String.valueOf(map.get("price"));
                    String currency = String.valueOf(map.get("currency"));
                    prices.setText(price + currency);
                } catch (Exception e) {
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