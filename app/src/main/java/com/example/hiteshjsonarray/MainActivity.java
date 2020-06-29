package com.example.hiteshjsonarray;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private Button button;
    private EditText city;
    private RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

     String baseURL="https://api.openweathermap.org/data/2.5/weather?q=";
      String  API="&appid=27e3262bd16de1188a49231ce14c863a";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.display);
        city = findViewById(R.id.editText1);
        button = findViewById(R.id.button);
        //initialise here
        requestQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String myURL=baseURL+ city.getText().toString() +API;

            //String myURL=baseURL + city.getText().toString() + API;
            //here when ever we create two method always there will two request first request is succefull second is request is unssucessfull
             JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, myURL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {  //respose is stored in response varible
                    try {
                           String info=jsonObject.getString("weather");
                        //here if array then we suse loop;
                        JSONArray ar=new JSONArray(info);
                        for(int i=0;i<ar.length();i++)
                        {
                            JSONObject parObj=ar.getJSONObject(i);
                            String myWeather=parObj.getString("main");

                            result.setText(myWeather);
                          //  Toast.makeText(MainActivity.this, "display "+myWeather, Toast.LENGTH_SHORT).show();


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
                    requestQueue.add(jsonObjectRequest);
//      requestQueue.add(Js);
            }
        });


    }
}


