package com.example.searchview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<SavetoModel> userinfo;
    Context context;

    public MyAdapter(List<SavetoModel> userinfo, Context context) {
        this.userinfo = userinfo;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.name.setText(userinfo.get(position).getSavename());
        holder.age.setText(userinfo.get(position).getSaveage());
        holder.roll.setText(String.valueOf(userinfo.get(position).getSaveroll()));
    }

    @Override
    public int getItemCount() {
        return userinfo.size();
    }

    public class MyViewHolder extends ViewHolder {

        TextView name, age, roll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameTextviewId);
            age = itemView.findViewById(R.id.ageTextviewId);
            roll = itemView.findViewById(R.id.rollTextviewId);


        }
    }
}
