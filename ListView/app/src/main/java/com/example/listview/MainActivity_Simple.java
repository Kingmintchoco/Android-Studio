package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_Simple extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        final String[] song = {"Dun Dun Dance", "ASAP", "Next Level"};

        lv = (ListView) findViewById(R.id.listView);

        /*

        ArrayAdapter <String> adapter0 =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, song);

         */

        /* Single Choice

        ArrayAdapter <String> adapter_single =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, song);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setAdapter(adapter_single);

         */

        // Multiple Choice
        ArrayAdapter <String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, song);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // select -> index / arg -> position or ID
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int select, long arg) {
                Toast.makeText(getApplicationContext(),
                        "Click: " + song[select] + "select=" + select + " arg=" + arg, Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "Long Click: " + song[i], Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
