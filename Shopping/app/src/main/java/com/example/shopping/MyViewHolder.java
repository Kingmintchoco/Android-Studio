package com.example.shopping;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView title, price;
    ImageView img;
    Button btn;

    public MyViewHolder(View itemView){
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.itemtitle);
        price = (TextView) itemView.findViewById(R.id.itemprice);
        img = (ImageView) itemView.findViewById(R.id.itemimgview);
        btn = (Button) itemView.findViewById(R.id.itembtn);
    }
}
