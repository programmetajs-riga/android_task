package com.example.andriod_test_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


import java.net.URL;
import java.util.ArrayList;

public class Main extends AppCompatActivity {



    String SportName[] ={"American Football" , "Basketball" , "Criket","Mixed Artials Arts","Rugby League","Soccer-Other"};
    int image[] = {R.drawable.rugby,R.drawable.basketball,R.drawable.cricket,R.drawable.box,R.drawable.rugby,R.drawable.soccer};
// test git



    ListView list;

    private static HttpURLConnection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent = new Intent(Main.this,ConnectionJson.class);
        startActivity(intent);

        super.onStart();
        list=findViewById(R.id.view);




        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),SportName,image);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    String id="1";

                    new ConnectionJson().execute(id);

                }else if(i==1){
                    String id="2";

                    new ConnectionJson().execute(id);

                }else if(i==2){
                    String id="3";

                    new ConnectionJson().execute(id);
                }else if(i==3){
                    String id="4";

                    new ConnectionJson().execute(id);
                }else if(i==4){
                    String id="5";

                    new ConnectionJson().execute(id);
                }else if(i==5){
                    String id="6";

                    new ConnectionJson().execute(id);
                }

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
                URL url = new URL("https://engine.free.beeceptor.com/api/getServices");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                readers = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int status = connection.getResponseCode();
                while((line = readers.readLine()) != null){

                    responseContent.append(line);
                }

                String content = responseContent.toString();

                JSONObject jsonObject =new JSONObject(content);

                String name = jsonObject.getString("name");
                String adress = jsonObject.getString("address");
                String phone = jsonObject.getString("phone");
                String price = jsonObject.getString("price");
                String currency = jsonObject.getString("currency");

                Intent intent = new Intent(Main.this,details.class);

                intent.putExtra("Value1",name);
                intent.putExtra("Value2",adress);
                intent.putExtra("Value3",phone);
                intent.putExtra("Value4",price);
                intent.putExtra("Value5",currency);
                startActivity(intent);
                finish();

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

   /* public class Json extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... strings) {
            String urlid=strings[0];
            BufferedReader readers;
            StringBuffer responseContent = new StringBuffer();


            try {
                String line;
                URL url = new URL("https://engine.free.beeceptor.com/api/getServices");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                readers = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = readers.readLine()) != null){

                    responseContent.append(line);
                }


                JSONArray jsonArray=new JSONArray(responseContent.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    name.add(jsonObject.getString("name"));

                }


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
    }*/


}