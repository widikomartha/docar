package com.example.docar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

public class DetailCar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_car);

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        Bundle bundle = getIntent().getExtras();
        ImageView foto_detail =(ImageView)findViewById(R.id.foto_detail);
        TextView tipe_detail = (TextView) findViewById(R.id.tipe_detail);
        TextView merek_detail = (TextView) findViewById(R.id.merek_detail);
        TextView plate_detail = (TextView) findViewById(R.id.plate_detail);
        TextView tahun_detail = (TextView) findViewById(R.id.tahun_detail);
        TextView harga_detail = (TextView) findViewById(R.id.harga_detail);

        Glide.with(this).load(bundle.getString("foto")).into(foto_detail);
        tipe_detail.setText( bundle.getString("tipe"));
        merek_detail.setText(bundle.getString("merek"));
        plate_detail.setText( bundle.getString("plate"));
        tahun_detail.setText(bundle.getString("tahun"));
        final String rpne = formatter.format(bundle.getInt("harga"));
        harga_detail.setText( "Rp. "+rpne);
    }
}