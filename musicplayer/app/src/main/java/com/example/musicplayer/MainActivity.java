package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    boolean PAUSED = false;

    static int count = 0;
    SeekBar sb;
    ImageButton play, previous, next;

    ListView lv;
    String[] song = {
            "꿈빛파티시엘", "도라에몽", "짱구는 못말려"
    };

    ArrayList<String> songList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("♪");

        play = (ImageButton) findViewById(R.id.play);
        previous = (ImageButton) findViewById(R.id.previous);
        next = (ImageButton) findViewById(R.id.next);

        sb = (SeekBar) findViewById(R.id.sb);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mp.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count == 0){
                    firstPlay();
                    makeThread();
                    ++count;
                }else{
                    if(PAUSED == false){
                        mp.pause();
                        makeThread();
                        play.setImageResource(R.drawable.play);
                        PAUSED = true;
                    }else{
                        mp.start();
                        makeThread();
                        PAUSED = false;
                        play.setImageResource(R.drawable.pause);
                    }
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lv = (ListView) findViewById(R.id.listview);

        songList = new ArrayList<String>();
        for(int i = 0; i < song.length; ++i){
            songList.add(song[i]);
        }

        adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }

    void firstPlay(){
        mp = MediaPlayer.create(getApplicationContext(), R.raw.song1);
        mp.setLooping(true);
        mp.start();
        play.setImageResource(R.drawable.pause);
    }

    void makeThread(){
        new Thread(){
            public void run(){
                // 음악이 계속 작동 중임
                final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

                if(mp == null) return;
                sb.setMax(mp.getDuration());  // 음악의 시간을 최대로 설정

                while(mp.isPlaying()){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sb.setProgress(mp.getCurrentPosition()); // runOnUIThread 에서 안해도 no error
                        }
                    });

                    SystemClock.sleep(100);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sb.setProgress(0);
                    }
                });
            }
        }.start();
    }
}