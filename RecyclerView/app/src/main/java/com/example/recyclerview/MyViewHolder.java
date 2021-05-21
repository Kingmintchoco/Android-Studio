package com.example.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv;
    ImageView img;
    Button btn;

    public MyViewHolder(View itemView){
        super(itemView);

        tv = (TextView) itemView.findViewById(R.id.itemtext);
        img = (ImageView) itemView.findViewById(R.id.itemimgview);
        btn = (Button) itemView.findViewById(R.id.itembtn);
    }
}
