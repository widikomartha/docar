package com.example.docar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button signin,signup;
    EditText email,password;

    String URL_DATA = "http://192.168.1.9/docar/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Deklarasi komponen view
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        signin = (Button) findViewById(R.id.signin);
        Button btnLogin =  (Button)findViewById(R.id.signin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLogin();

            }
        });

        TextView register = (TextView) findViewById(R.id.txt_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    private void sendLogin() {
        //  Toast.makeText(getApplicationContext(), "akses loginne ", Toast.LENGTH_LONG).show();
        // Setting POST request ke server
        StringRequest loginRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle response dari server ketika sukses dengan mengkonvert menjadi JSON
                        try {
                            JSONObject json = new JSONObject(response);
                            // Mengambil variable status pada response
                            String status = json.getString("message");


                            Toast.makeText(getApplicationContext(), " Login : "+status, Toast.LENGTH_LONG).show();
                            if(status.equals("Success")){

                                // Jika Login Sukses Maka pindah ke activity lain.
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                // Jika Login Gagal Maka mengeluarkan Toast dengan message.
                                Toast.makeText(getApplicationContext(), "Username & Password Salah", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle response dari server ketika gagal
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };
        // Buat antrian request pada cache android
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Tambahkan Request pada antrian request
        requestQueue.add(loginRequest);
    }
}
