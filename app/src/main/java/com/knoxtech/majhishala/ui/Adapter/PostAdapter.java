package com.knoxtech.majhishala.ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knoxtech.majhishala.Player.PlayerActivity;
import com.knoxtech.majhishala.R;
import com.knoxtech.majhishala.ui.Model.ItemData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    Context context;
    ArrayList<ItemData> profiles;

    public PostAdapter(Context c , ArrayList<ItemData> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_more, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleName.setText(profiles.get(position).getName());

        Picasso.get().load(profiles.get(position).getImage()).into(holder.thumbBanner);

        holder.itemView.setOnClickListener(view -> {
           // Toast.makeText(context, ""+profiles.get(position).getUrl(), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context, PlayerActivity.class);
                intent.putExtra("videoUrl",profiles.get(position).getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleName;
        ImageView thumbBanner;
        public MyViewHolder(View itemView) {
            super(itemView);
            titleName=itemView.findViewById(R.id.thumbName);
            thumbBanner=itemView.findViewById(R.id.thumbBanner);
        }

    }




}
