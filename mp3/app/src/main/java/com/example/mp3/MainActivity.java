package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothClass;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    Button btnPlay, btnPause, btnStop;

    MediaPlayer mPlayer;
    boolean PAUSED = false; // 음악이 일시정지 중인지 체크

    TextView tvMP3;
    ProgressBar pb;

    String selectMP3;

    Switch switch1;
    SeekBar sb;
    TextView tvTime;

    MusicService ms; // for bind service
    boolean isService = false;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.MusicServiceBinder mb = (MusicService.MusicServiceBinder) iBinder;
            ms = mb.getService();
            isService = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MP3");

        btnPlay = (Button) findViewById(R.id.listen);
        btnStop = (Button) findViewById(R.id.stop);
        btnPause = (Button) findViewById(R.id.pause);

        tvMP3 = (TextView) findViewById(R.id.curmp3);
        pb = (ProgressBar) findViewById(R.id.pb);

        selectMP3 = "짱구는 못말려 OP";

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.op);
                // mPlayer.prepare();
                mPlayer.setLooping(true);
                mPlayer.start();

                btnPlay.setClickable(false);
                btnPause.setClickable(true);
                btnStop.setClickable(true);

                tvMP3.setText("♬ - " + selectMP3);
                pb.setVisibility(View.VISIBLE);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PAUSED == false){
                    mPlayer.pause();
                    btnPause.setText("이어듣기");
                    PAUSED = true;

                    pb.setVisibility(View.INVISIBLE);
                }else{
                    mPlayer.start();
                    PAUSED = false;
                    btnPause.setText("일시정지");

                    pb.setVisibility(View.VISIBLE);
                }
            }
        });
        btnPause.setClickable(false);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                mPlayer.reset();

                btnPlay.setClickable(true);
                btnPause.setClickable(false);

                btnPause.setText("일시정지");
                btnStop.setClickable(false);

                tvMP3.setText("♬");
                pb.setVisibility(View.INVISIBLE);
            }
        });
        btnStop.setClickable(false);

        sb = (SeekBar) findViewById(R.id.sb);
        switch1 = (Switch) findViewById(R.id.switch1);
        tvTime = (TextView) findViewById(R.id.curmusicTime);

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked()){
                    mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.op);
                    mPlayer.start();
                    makeThread();
                }else{
                    mPlayer.stop();
                }
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    void makeThread(){
        new Thread(){
            public void run(){
                // 음악이 계속 작동 중임
                final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

                if(mPlayer == null) return;
                sb.setMax(mPlayer.getDuration());  // 음악의 시간을 최대로 설정

                while(mPlayer.isPlaying()){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sb.setProgress(mPlayer.getCurrentPosition()); // runOnUIThread 에서 안해도 no error
                            tvTime.setText(String.format(timeFormat.format(mPlayer.getCurrentPosition())));
                        }
                    });

                    SystemClock.sleep(100);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sb.setProgress(0);
                        switch1.setChecked(false);
                        tvTime.setText(String.format(timeFormat.format(0)));
                    }
                });
            }
        }.start();
    }

    // service

    public void serviceStart(View v){
        Intent intent = new Intent(this, MusicService.class);

        intent.putExtra("song", R.raw.op);
        startService(intent);

        // bindService(intent, conn, Context.BIND_AUTO_CREATE); // for bind service

        Log.i("CHOI", "startService()");
    }

    public void serviceStop(View v){
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);

        // unbindService(conn); // for bind service

        Log.i("CHOI", "stopService()");
    }

    public void servicePlay(View v){
        if(isService){
            ms.play(R.raw.op);
            makeThread();
        }
    }
}