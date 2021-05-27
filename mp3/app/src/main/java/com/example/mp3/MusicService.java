package com.example.mp3;

import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.Provider;

public class MusicService extends Service {
    MediaPlayer mp;

    IBinder mBinder = new MusicServiceBinder();

    class MusicServiceBinder extends Binder {
        MusicService getService() { return MusicService.this; }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        Log.i("CHOI", "onBind()");
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.i("CHOI", "onCreate()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i("CHOI", "onDestroy()");
        if(mp != null) mp.stop();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("CHOI", "onStartCommand()");
        mp = MediaPlayer.create(this, intent.getIntExtra("song", 0));
        mp.setLooping(true);
        mp.start();

        return START_STICKY;
        // return super.onStartCommand(intent, flags, startId);
    }

    // for bind method
    public void play(int song){
        mp = MediaPlayer.create(this, song);
        mp.setLooping(true);
        mp.start();
    }

    public MediaPlayer getMP(){
        return mp;
    }
}
