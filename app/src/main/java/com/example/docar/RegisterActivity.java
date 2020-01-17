package com.example.docar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    String URL_DATA = "http://192.168.43.87/docar/register.php";
    String tempUsername, tempEmail, tempPass, temptNoTelp, temptNIK;
    EditText username,email,password, repassword, no_telp,  nik;
    //RadioButton JKL, JKP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ////////////////
        username = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        email = (EditText) findViewById(R.id.email);
        no_telp = (EditText) findViewById(R.id.phone);
        /*JKL = (RadioButton) findViewById(R.id.jkl);
        JKP = (RadioButton) findViewById(R.id.jkp);*/
        nik = (EditText) findViewById(R.id.nik);
        ////////////////

        TextView txt_register = (TextView) findViewById(R.id.txt_login);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        TextView btn_regis = (Button) findViewById(R.id.signup);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                InsertData(tempUsername, tempEmail, tempPass, temptNoTelp, temptNIK);

                /*Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);*/
            }
        });
    }


    public void getData()
    {
        tempUsername=username.getText().toString();
        tempPass=password.getText().toString();
        tempEmail=email.getText().toString();
        temptNoTelp=no_telp.getText().toString();
        temptNIK=nik.getText().toString();
    }


    public void InsertData(final String sUsername, final String sEmail, final String sPass, final String
            sNotelp, final String sNik )
    {

        Toast.makeText(getBaseContext(),"Klik Register",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            String jsnMsg = jsonObject.getString("message");
                            Toast.makeText(getBaseContext(),"Save Data"+" "+jsnMsg,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<>();
                params.put("username",sUsername);
                params.put("email",sEmail);
                params.put("password",sPass);
                params.put("no_tlp",sNotelp);
                params.put("nik",sNik);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}