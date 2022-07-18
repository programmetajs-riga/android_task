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

        Button btn = findViewById(R.id.backbtn); // что за btn? для чего это кнопка

        names=findViewById(R.id.name); // оступы "..s = f..."
       address=findViewById(R.id.adress); // оступы "..s = f..." и слева табуляция
        phone=findViewById(R.id.phone); // оступы "..e = f..." и слева табуляция
        prices=findViewById(R.id.price); // _..._


        id= getIntent().getStringExtra("Value1"); // что значит Value1 ?

        new ConnectionJson().execute(id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

    }

   public class ConnectionJson extends AsyncTask<String, String, String> {

// зачем такой конский отступ ?

        @Override
        protected String doInBackground(String... strings) {
            String urlid=strings[0]; // не лучшее обращение к первому элементу. лучше через .first()
            BufferedReader readers;
            StringBuffer responseContent = new StringBuffer();
// зачем такой конский отступ ?

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


                String name = jsonObject.getString("name");  // переделать и обеденить в dictionary / object
                String adress = jsonObject.getString("address"); // переделать и обеденить в dictionary / object
                String phones = jsonObject.getString("phone"); // переделать и обеденить в dictionary / object
                try{
                    String price = jsonObject.getString("price"); // переделать и обеденить в dictionary / object
                    String currency = jsonObject.getString("currency"); // переделать и обеденить в dictionary / object
                    prices.setText(price + currency);
                }catch (JSONException e){
                    e.printStackTrace();
                }
// зачем такой конский отступ ?

                names.setText(name);
                address.setText(adress);
                phone.setText(phones);;
// зачем такой конский отступ ?


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
// зачем такой конский отступ ?

    }
    public void back(){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }
}