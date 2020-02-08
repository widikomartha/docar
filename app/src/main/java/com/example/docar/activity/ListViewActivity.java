package com.example.docar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.docar.DetailCar;
import com.example.docar.R;
import com.example.docar.listview.ListViewArrayAdapter;
import com.example.docar.model.SetValue_Car;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity{

    private static  final String URL_DATA = "http://192.168.43.87/docar/cars.php";
    private ListView listTotal;
    private ListViewArrayAdapter ListViewArrayAdapter;
    private List<SetValue_Car> setValue_carList;

//    private void showList(){
//        listTotal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Bundle bundle = new Bundle();
//                bundle.putString("id", setValue_perangkatsList.get(position).getId().toString());
//                bundle.putString("nama_perangkat", setValue_perangkatsList.get(position).getNama_perangkats().toString());
//                bundle.putString("nama_layanan", setValue_perangkatsList.get(position).getNama_layanan().toString());
//                bundle.putString("tipe_objeks", setValue_perangkatsList.get(position).getTipe_objeks().toString());
//                bundle.putString("created_at", setValue_perangkatsList.get(position).getCreated_at().toString());
//
//                Intent intent = new Intent(DrawerActivity.this, com.example.checklistandroid.listView.DetailChecklistActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
//    }

    private void loadCar() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "onResponse", Toast.LENGTH_LONG).show();
                try {
                    setValue_carList = new ArrayList<>();
                    JSONObject obj = new JSONObject(response);
                    JSONArray galang_danaArray = obj.getJSONArray("data");

                    Toast.makeText(getApplicationContext(),"datas",Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < galang_danaArray.length(); i++) {
                        JSONObject carObject = galang_danaArray.getJSONObject(i);
                        SetValue_Car car = new SetValue_Car(
                                carObject.getString("id"),
                                carObject.getString("tipe"),
                                carObject.getString("merek"),
                                carObject.getString("plate"),
                                carObject.getString("tahun"),
                                carObject.getInt("harga"),
                                carObject.getString("foto")

                        );
                        setValue_carList.add(car);
                    }
                    ListViewArrayAdapter adapter = new ListViewArrayAdapter(getApplicationContext(),setValue_carList);
                    listTotal.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

//        showList();

        listTotal = (ListView) findViewById(R.id.list_view);

        loadCar();

        listTotal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),
                //     setValueArtikelAwalList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString("tipe", setValue_carList.get(position).getTipe().toString());
                bundle.putString("merek", setValue_carList.get(position).getMerek().toString());
                bundle.putString("plate", setValue_carList.get(position).getPlate().toString());
                bundle.putString("tahun", setValue_carList.get(position).getTahun().toString());
                bundle.putInt("harga", setValue_carList.get(position).getHarga());
                bundle.putString("foto", setValue_carList.get(position).getFoto().toString());

                Intent intent = new Intent(ListViewActivity.this, DetailCar.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
