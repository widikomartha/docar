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

    String URL_DATA = "http://192.168.1.6/docar/register.php";
    String tempName, tempEmail, tempPass, temptNoTlp;
    EditText name,email,password, repassword, no_tlp;
    //RadioButton JKL, JKP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ////////////////
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        email = (EditText) findViewById(R.id.email);
        no_tlp = (EditText) findViewById(R.id.phone);
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
                InsertData(tempName, tempEmail, tempPass, temptNoTlp);

                /*Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);*/
            }
        });
    }


    public void getData()
    {
        tempName=name.getText().toString();
        tempPass=password.getText().toString();
        tempEmail=email.getText().toString();
        temptNoTlp = no_tlp.getText().toString();
    }


    public void InsertData(final String sName, final String sEmail, final String sPass, final String
            sNotlp)
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
                params.put("name",sName);
                params.put("email",sEmail);
                params.put("password",sPass);
                params.put("no_tlp",sNotlp);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}