package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mlayoutmanager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mlayoutmanager);

        ArrayList<PaintTitle> Dataset = new ArrayList<PaintTitle>();
        Dataset.add(new PaintTitle(R.drawable.musk, "melon"));
        Dataset.add(new PaintTitle(R.drawable.milk, "milk"));
        Dataset.add(new PaintTitle(R.drawable.banana, "banana"));
        Dataset.add(new PaintTitle(R.drawable.strawberry, "strawberry"));
        Dataset.add(new PaintTitle(R.drawable.mango, "mango"));
        Dataset.add(new PaintTitle(R.drawable.coconut, "coconut"));

        mAdapter = new MyAdapter(Dataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}