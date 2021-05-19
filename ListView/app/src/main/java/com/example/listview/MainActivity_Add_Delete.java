package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity_Add_Delete extends AppCompatActivity {

    ListView lv;
    EditText ed;

    ArrayList<String> songList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_delete);

        lv = (ListView) findViewById(R.id.listView);
        ed = (EditText) findViewById(R.id.editText);

        songList = new ArrayList<String>();
        songList.add("마.피.아 In the morning");
        songList.add("한발짝 두발짝");

        adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "Click: " + songList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                songList.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        /*

        final String[] songAddList =
                {"Don't call me", "Hi Trash-girl", "Blueming"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter;

        spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, songAddList
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                songList.add(songAddList[i]);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                Toast.makeText(
                        getApplicationContext(), "nothing selected", Toast.LENGTH_SHORT
                ).show();
            }
        });

         */

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // res에서 arr 가져오는 방법
        final String[] song_names = getResources().getStringArray(R.array.song_names);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.song_names, android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                songList.add(song_names[i]);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                Toast.makeText(getApplicationContext(),
                        "nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        spinner.setAdapter(spinnerAdapter);
    }

    public void addClick(View v){
        songList.add(ed.getText().toString());
        adapter.notifyDataSetChanged();
    }
}
