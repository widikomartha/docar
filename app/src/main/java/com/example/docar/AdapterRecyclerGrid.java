package com.example.docar;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterRecyclerGrid extends RecyclerView.Adapter<AdapterRecyclerGrid.MyHolder> {
    Context context;
    ArrayList<MenuLayout> arrayList;
    public AdapterRecyclerGrid(Context context, ArrayList<MenuLayout>arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i){
        View view= LayoutInflater.from(context).inflate(R.layout.custom_list,viewGroup,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }
    @Override
	    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
                    myHolder.title.setText(arrayList.get(i).getTitle());
        	        myHolder.image.setImageResource(arrayList.get(i).getImages());
        	    }

        	    @Override
	    public int getItemCount() {
        	        return arrayList.size();
        	    }
        	    public static class MyHolder extends RecyclerView.ViewHolder {
	        		TextView title;
	        		ImageView image;
					private Context context;
        	        public MyHolder(@NonNull final View itemView) {
            	            super(itemView);
							context = itemView.getContext();
            	            title = itemView.findViewById(R.id.text_design);
            	            image = itemView.findViewById(R.id.image_design);
            	            itemView.setOnClickListener(new View.OnClickListener() {
	                @Override
	                public void onClick(View v)
					{
						Intent intent = new Intent();
						Context c = v.getContext();
						Toast.makeText(c,"Hello Bro Selamat Datang",Toast.LENGTH_SHORT);
						switch (getAdapterPosition()){
							case 0 :
								Toast.makeText(itemView.getContext(),"You have selected :"+getAdapterPosition(), Toast.LENGTH_SHORT).show();

								break;
							case 1 :

								break;
							case 2 :
								break;

						}
						context.startActivity(intent);					}
	            });
        	        }
	    }
	}
