package com.example.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    ImageView infoimg;
    TextView infotitle, infoprice;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informationview);

        Intent intent = getIntent();
        PaintTitle info = (PaintTitle) intent.getSerializableExtra("정보");

        infoimg = (ImageView) findViewById(R.id.infoimage);
        infotitle = (TextView) findViewById(R.id.infotitle);
        infoprice = (TextView) findViewById(R.id.infoprice);

        infotitle.setText(info.title);
        infoprice.setText(info.price);
        infoimg.setImageResource(info.imgID);

    }
}
