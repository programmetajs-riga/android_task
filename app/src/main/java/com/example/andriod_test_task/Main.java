package com.example.andriod_test_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
// зачем такой конский отступ ?


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
// зачем такой конский отступ ?

import java.net.URL;
import java.util.ArrayList;

public class Main extends AppCompatActivity {
// зачем такой конский отступ ?

    String stringArray[]={};
    int image[] = {R.drawable.rugby,R.drawable.basketball,R.drawable.cricket,R.drawable.box,R.drawable.rugby,R.drawable.soccer};
    ListView list;
    String ID[];
    String id;
    private static HttpURLConnection connection;
// зачем такой конский отступ ?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list=findViewById(R.id.view);

        new ConnectionJson().execute();
// зачем такой конский отступ ?


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){ // зачем эта проверка, что она проверяет? зачем дублировать одно и тоже в 6 экземплярах?
                     id=ID[i];

                    Intent intent = new Intent(Main.this,details.class);

                    intent.putExtra("Value1",id);  // что значит Value1 ?
                    startActivity(intent);

                }else if(i==1){ // зачем эта проверка, что она проверяет? зачем дублировать одно и тоже в 6 экземплярах?
                     id=ID[i];
// зачем такой конский отступ ?

                    Intent intent = new Intent(Main.this,details.class);

                    intent.putExtra("Value1",id);  // что значит Value1 ?
                    startActivity(intent);

                }else if(i==2){ // зачем эта проверка, что она проверяет? зачем дублировать одно и тоже в 6 экземплярах?
                     id=ID[i];

                    Intent intent = new Intent(Main.this,details.class);

                    intent.putExtra("Value1",id);  // что значит Value1 ?
                    startActivity(intent);
                }else if(i==3){ // зачем эта проверка, что она проверяет? зачем дублировать одно и тоже в 6 экземплярах?
                     id=ID[i];

                    Intent intent = new Intent(Main.this,details.class);

                    intent.putExtra("Value1",id); // что значит Value1 ?
                    startActivity(intent);
                }else if(i==4){ // зачем эта проверка, что она проверяет? зачем дублировать одно и тоже в 6 экземплярах?
                     id=ID[i];

                    Intent intent = new Intent(Main.this,details.class);

                    intent.putExtra("Value1",id); // что значит Value1 ?
                    startActivity(intent);
                }else if(i==5){ // зачем эта проверка, что она проверяет? зачем дублировать одно и тоже в 6 экземплярах?
                     id=ID[i];

                    Intent intent = new Intent(Main.this,details.class);

                    intent.putExtra("Value1",id); // что значит Value1 ?
                    startActivity(intent);
                }

            }
        });


    }



    public class ConnectionJson extends AsyncTask<String, String, String[]> {
// зачем такой конский отступ ?


        @Override
        protected String[] doInBackground(String... string) {

            BufferedReader readers;
            StringBuffer responseContent = new StringBuffer();
// зачем такой конский отступ ?

            try {
                String line;
                URL url = new URL("https://engine.free.beeceptor.com/api/getServices");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                readers = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = readers.readLine()) != null){
// зачем такой конский отступ ?
                    responseContent.append(line);
                }

                ArrayList<String> name = new ArrayList<>(); // переделать и обеденить в dictionary
                ArrayList<String> idjson = new ArrayList<>();// переделать и обеденить в dictionary
// зачем такой конский отступ ?

                JSONArray jsonArray=new JSONArray(responseContent.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    name.add(jsonObject.getString("name")); // переделать и обеденить в dictionary
                    idjson.add(jsonObject.getString("id"));// переделать и обеденить в dictionary
                    stringArray = name.toArray(new String[i]); // переделать и обеденить в dictionary
                    ID = idjson.toArray(new String[i]); // переделать и обеденить в dictionary
                }

                return ID;

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

        @Override
        protected void onPostExecute(String[] strings){
            super.onPostExecute(strings);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),stringArray,image);
            list.setAdapter(adapter);

        }
// зачем такой конский отступ ?

    }
// зачем такой конский отступ ?

}
