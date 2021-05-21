package com.example.listview;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity_Custom extends AppCompatActivity {
    ListView lv;
    ArrayList<PaintTitle> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        lv = (ListView) findViewById(R.id.listView);

        data = new ArrayList<PaintTitle>();
        data.add(new PaintTitle(R.drawable.cat1, "cat1"));
        data.add(new PaintTitle(R.drawable.cat2, "cat2"));

        MyBaseAdapter adapter = new MyBaseAdapter(this, data);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "Click: position = " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "Long Click: position = " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}

class PaintTitle{
    int imgID;
    String title;

    public PaintTitle(int id, String str){
        imgID = id;
        title = str;
    }
}

class MyBaseAdapter extends BaseAdapter {

    Context mContext = null;
    ArrayList<PaintTitle> mData = null;

    public MyBaseAdapter(Context context, ArrayList<PaintTitle> data){
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
        // return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public PaintTitle getItem(int position){
        return mData.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View itemLayout;

        int newposition = position % 2;

        if(convertView == null){
            itemLayout = View.inflate(mContext, R.layout.listview_item, null);
            Log.d("CHOI", "convertView = null, pos = " + position);
        }else{
            itemLayout = convertView;
            Log.d("CHOI", "convertView != null, pos = " + position);
        }

        ImageView imgView = (ImageView) itemLayout.findViewById(R.id.imgView);
        imgView.setImageResource(mData.get(newposition).imgID);

        TextView txtView = (TextView) itemLayout.findViewById(R.id.textItemCustom);
        txtView.setText(mData.get(newposition).title);

        return itemLayout;
    }
}
