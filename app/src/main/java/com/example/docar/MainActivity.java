package com.example.docar;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<MenuLayout>arrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterRecyclerGrid adapterRecyclerGrid;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridList();
        recyclerView=findViewById(R.id.recyclerview_grid);
        layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        adapterRecyclerGrid=new AdapterRecyclerGrid(this,arrayList);
        recyclerView.setAdapter(adapterRecyclerGrid);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


    private void gridList(){
        arrayList=new ArrayList<>();
        arrayList.add(new MenuLayout("Car Only", R.drawable.car) );
        arrayList.add(new MenuLayout("Car + Driver", R.drawable.car_driver) );
        arrayList.add(new MenuLayout( "Airport Transfer", R.drawable.airport) );

    }


}
