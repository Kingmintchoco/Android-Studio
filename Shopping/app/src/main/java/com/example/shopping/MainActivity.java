package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RecyclerView;
    private RecyclerView.Adapter Adapter;
    private RecyclerView.LayoutManager layoutmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutmanager = new LinearLayoutManager(this);

        RecyclerView.setLayoutManager(layoutmanager);

        ArrayList<PaintTitle> Dataset = new ArrayList<PaintTitle>();
        Dataset.add(new PaintTitle(R.drawable.musk, "melon", "1,000"));
        Dataset.add(new PaintTitle(R.drawable.milk, "milk", "2,000"));
        Dataset.add(new PaintTitle(R.drawable.banana, "banana", "3,000"));

        Adapter = new MyAdapter(Dataset);
        RecyclerView.setAdapter(Adapter);
    }
}
