package com.example.recyclerview;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<PaintTitle> mData;

    public MyAdapter(ArrayList<PaintTitle> data) {
        mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, parent, false);
        // View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        Log.d("CHOI", "onCreateViewHolder");

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        holder.img.setImageResource(mData.get(position % 6).imgID);
        holder.tv.setText(mData.get(position % 6).title);

        final int newpos = position % 6;
        final Context myContext = holder.itemView.getContext();

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myContext, mData.get(newpos).title, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }
}
