package com.knoxtech.majhishala.ui.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knoxtech.majhishala.ActivityMore;
import com.knoxtech.majhishala.Player.PlayerActivity;
import com.knoxtech.majhishala.R;

import com.knoxtech.majhishala.ui.Interface.IItemClickListener;
import com.knoxtech.majhishala.ui.Model.ItemData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    private Context context;
    private List<ItemData> itemDataList;

    public MyItemAdapter(Context context, List<ItemData> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_item_title;
        ImageView img_item;
        IItemClickListener iItemClickListener;

        public void setiItemClickListener(IItemClickListener iItemClickListener){
            this.iItemClickListener=iItemClickListener;
        }
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

//            pair1 =Pair.create(,"myImage");
          //  pair2 =Pair.create(txt_item_title,"myTitle");

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iItemClickListener.onItemClickListener(view,getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

//        final String name= itemDataList.get(position).getName();
//        final String image= itemDataList.get(position).getImage();
        holder.txt_item_title.setText(itemDataList.get(position).getName());
        Picasso.get().load(itemDataList.get(position).getImage()).into(holder.img_item);


    //    holder.setiItemClickListener((view, position1) ->

        holder.setiItemClickListener((view, position1) -> {
            Intent intent=new Intent(context, PlayerActivity.class);
            intent.putExtra("videoUrl",itemDataList.get(position1).getUrl());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return (itemDataList !=null ? itemDataList.size() : 0);
    }

}
