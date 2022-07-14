package com.example.andriod_test_task;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Login extends AppCompatActivity {


    private static HttpURLConnection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btn = findViewById(R.id.btnlogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Login.this, Main.class);
                startActivity(intent);

           // метод назодиться в коментах чтобы не тратить драгоценные 50 запросов в день!
                // new  PostMethod().execute();



            }
        });
    }


    public class PostMethod extends AsyncTask<String , Void ,String> {


        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("https://engine.free.beeceptor.com/api/login");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                try {
                    JSONObject obj = new JSONObject();
                    EditText username = (EditText) findViewById(R.id.login);
                    username.getText().toString();
                    EditText password = (EditText) findViewById(R.id.password);
                    password.getText().toString();
                    obj.put("username", username);
                    obj.put("password", password);
                    wr.writeBytes(obj.toString());
                    wr.flush();
                    wr.close();
                    urlConnection.connect();
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        Intent intent = new Intent(Login.this, Main.class);
                        startActivity(intent);
                        return null;

                    } else {
                        urlConnection.disconnect();
                        TextView textView = (TextView) findViewById(R.id.errortext);
                        textView.setText("Incorrect login or password");
                    }
                    urlConnection.disconnect();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

}