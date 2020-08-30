package com.knoxtech.majhishala;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[] values;
    Context context1;
    public RecyclerViewAdapter(Context context2,String[] values2){
        values = values2;
        context1 = context2;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.textview1);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view1 = LayoutInflater.from(context1).inflate(R.layout.recycler_view_items,parent,false);

        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position){

        Vholder.textView.setText(values[position]);


        Vholder.textView.setTextColor(Color.BLACK);
        String text=Vholder.textView.getText().toString();
        Vholder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context1,MainActivity.class);
            intent.putExtra("startId",text);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context1.startActivity(intent);

        });

    }

    @Override
    public int getItemCount(){

        return values.length;
    }
}
