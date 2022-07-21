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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main extends AppCompatActivity {
    String[] stringArray ={};
    int image[] = {R.drawable.rugby,R.drawable.basketball,R.drawable.cricket,R.drawable.box,R.drawable.rugby,R.drawable.soccer};
    ListView list;
    String ID[];
    String id;
    private static HttpURLConnection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list=findViewById(R.id.view);
        new ConnectionJson().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    id=ID[i];
                    Intent intent = new Intent(Main.this,details.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
            }
        });
    }

    public class ConnectionJson extends AsyncTask<String, String, String[]> {

        @Override
        protected String[] doInBackground(String... string) {
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
                HashMap<String, Object> name = new HashMap<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    name.put(String.valueOf(i), jsonObject.getString("name"));
                }
                String[] keys = new String[name.size()];
                Object[] values = new Object[name.size()];
                int index = 0;
                for (Map.Entry<String, Object> mapEntry : name.entrySet()) {
                    keys[index] = mapEntry.getKey();
                    values[index] = mapEntry.getValue();
                    stringArray = Arrays.copyOf(values, values.length, new String[index].getClass());
                    index++;
                }
                HashMap<String, String> mapId = new HashMap<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    mapId.put(String.valueOf(i), jsonObject.getString("id"));
                    ID = mapId.values().toArray(new String[i]);
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
    }
}
