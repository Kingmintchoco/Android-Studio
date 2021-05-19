package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("§ M A I N §");
    }

    public void simpleClick(View v){
        Intent intent = new Intent(this, MainActivity_Simple.class);
        startActivity(intent);
    }

    public void addClick(View v){
        Intent intent = new Intent(this, MainActivity_Add_Delete.class);
        startActivity(intent);
    }

    public void customClick(View v){
        Intent intent = new Intent(this, MainActivity_Custom.class);
        startActivity(intent);
    }
}