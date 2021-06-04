package com.example.receiverex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Battery");
        battery = (TextView) findViewById(R.id.battery);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, iFilter);
    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(intent.ACTION_BATTERY_CHANGED)){
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                battery.setText(remain + "%" + "\n");

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch(plug){
                    case 0:
                        battery.append("not plugged");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        battery.append("ac plugged");
                        break;
                        case BatteryManager.BATTERY_PLUGGED_USB:
                            battery.append("usb plugged");
                            break;
                }
            }
        }
    };
}