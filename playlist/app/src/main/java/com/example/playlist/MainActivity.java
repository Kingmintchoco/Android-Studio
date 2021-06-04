package com.example.playlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView timetxt, datetxt;
    ImageView curTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timetxt = (TextView) findViewById(R.id.clock_time);
        datetxt = (TextView) findViewById(R.id.clock_date);
        curTime = (ImageView) findViewById(R.id.cur_time);

        long now = System.currentTimeMillis();
        Date time = new Date(now);
        Date date = new Date(now);

        SimpleDateFormat tdf = new SimpleDateFormat("hh:mm");
        SimpleDateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");

        String getTime = tdf.format(time);
        String getDate = ddf.format(date);

        timetxt.setText(getTime);
        datetxt.setText(getDate);

    }

    public void changeImg(){
    }
}