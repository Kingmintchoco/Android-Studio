package com.example.ohmygirl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] member = getResources().getStringArray(R.array.member);
        final Integer[] postID = {
                R.drawable.uah, R.drawable.arin, R.drawable.mimi, R.drawable.jihyo,
                R.drawable.seunghee, R.drawable.hyojung, R.drawable.vini
        };

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter <String> adapter;
        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,member
        );
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView img = (ImageView) findViewById(R.id.imgView);
                img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                img.setPadding(5,5,5, 5);
                img.setImageResource(postID[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}