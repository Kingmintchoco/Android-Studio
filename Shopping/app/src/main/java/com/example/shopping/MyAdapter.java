package com.example.shopping;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

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
    public void onBindViewHolder(MyViewHolder holder, final int position){

        holder.img.setImageResource(mData.get(position % 3).imgID);
        holder.title.setText(mData.get(position % 3).title);
        holder.price.setText(mData.get(position % 3).price);

        final int newpos = position % 3;
        final Context myContext = holder.itemView.getContext();

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InformationActivity.class);

                intent.putExtra("정보", new PaintTitle(mData.get(position%3).imgID,
                        mData.get(position%3).title, mData.get(position % 3).price));

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }
}
