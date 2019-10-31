package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Button button = (Button) findViewById(R.id.btn_show_login);
//        button.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                Log.i("Button","Button login clicked");
//            }
//        });
    }

    public void openNewIntent(View view){
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        Log.i("Information",message);
        startActivity(intent);
    }

    public void sendVolleyRequest(View view){
        final TextView textView = (TextView) findViewById(R.id.result_volley);

        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        String url ="https://sinergy-dev.xyz/tisygy/getPerformanceByClient?_=1572253836845&client=BJBR";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    textView.setText("Response: " + response.toString());
                    Log.i("Information","Response is: "+ response.toString());
//                    JSONArray data = null;
//                    try {
//                        data = response.getJSONArray("data");
//                        for (int i = 0; i < data.length();i++){
//                            JSONObject ticketing = data.getJSONObject(i);
//                            Log.i("ticketing",ticketing.getString("id_ticket"));
//                        }
////                        Log.i("Data", String.valueOf(data));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("That didn't work!");
                    Log.e("Volley_Error", String.valueOf(error));
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);




    }

    public void showLoginLayout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        Log.i("Information",message);
        startActivity(intent);
    }
}
