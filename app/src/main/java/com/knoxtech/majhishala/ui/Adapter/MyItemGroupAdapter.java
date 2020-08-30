package com.knoxtech.majhishala.ui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.knoxtech.majhishala.ActivityMore;
import com.knoxtech.majhishala.MainActivity;
import com.knoxtech.majhishala.R;
import com.knoxtech.majhishala.ui.Model.ItemData;
import com.knoxtech.majhishala.ui.Model.ItemGroup;

import java.util.List;
import java.util.Objects;


public class MyItemGroupAdapter extends RecyclerView.Adapter<MyItemGroupAdapter.MyViewHolder> {

    private Context context;
    private List<ItemGroup> dataList;

    public MyItemGroupAdapter(Context context, List<ItemGroup> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_title,btnMore;
        RecyclerView recyclerView_item_list;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_title=itemView.findViewById(R.id.itemTitle);
            btnMore=itemView.findViewById(R.id.btnMore);
            recyclerView_item_list=itemView.findViewById(R.id.recycler_view_list);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.layout_group,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.item_title.setText(dataList.get(position).getHeaderTitle());

        List<ItemData> itemData = dataList.get(position).getListItem();

        MyItemAdapter itemListAdapter=new MyItemAdapter(context,itemData);
        holder.recyclerView_item_list.setHasFixedSize(true);
        holder.recyclerView_item_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView_item_list.setAdapter(itemListAdapter);

        holder.recyclerView_item_list.setNestedScrollingEnabled(false);

        holder.btnMore.setOnClickListener(view -> {
            Bundle bundle = ((Activity)context).getIntent().getExtras();
            assert bundle != null;
            String standard = bundle.getString("startId");
            String subject=holder.item_title.getText().toString();


            Intent intent=new Intent(context, ActivityMore.class);
            intent.putExtra("std",standard);
            intent.putExtra("sub",subject);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            //+holder.item_title.getText()
            //Toast.makeText(context, ""+standard, Toast.LENGTH_SHORT).show();
            //context.startActivity(new Intent(context, ActivityMore.class));
        });
    }

    @Override
    public int getItemCount() {
        return (dataList !=null ? dataList.size() : 0);
    }


}
