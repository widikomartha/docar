package com.example.docar.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.docar.model.SetValue_Car;
import com.example.docar.R;

import java.text.DecimalFormat;
import java.util.List;


public class ListViewArrayAdapter extends ArrayAdapter<SetValue_Car> {
    private Context context;
    private List<SetValue_Car> setValue_carList;

    public ListViewArrayAdapter(Context context, List<SetValue_Car> setValue_carList){
        super(context, R.layout.list_car,setValue_carList);
        this.setValue_carList=setValue_carList;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.list_car,null,true);
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        ImageView foto =(ImageView)v.findViewById(R.id.imageView);
        TextView tipe = (TextView) v.findViewById(R.id.textTipe);
        TextView merek =(TextView) v.findViewById(R.id.textMerek);
        TextView harga =(TextView) v.findViewById(R.id.textHarga);

        //TextView test =(TextView) v.findViewById(R.id.test);
        // TextView descriptions =(TextView) v.findViewById(R.id.textDesc);


        SetValue_Car setValue_car = setValue_carList.get(position);

        /* img.setImageResource(setValue_ArtikelAwal.getImageId());*/
        Glide.with(context).load(setValue_car.getFoto()).into(foto);
        tipe.setText(setValue_car.getTipe());
        merek.setText(setValue_car.getMerek());
        harga.setText("Rp " + formatter.format(setValue_car.getHarga()));

        return v;
    }
}