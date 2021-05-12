package com.example.drawlinetouchex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2, RECT = 3;
    static int curShape = LINE;

    LinearLayout canvas;
    ImageButton red, blue, black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyGraphicView v = new MyGraphicView(this);
        canvas = findViewById(R.id.canvas);
        red = (ImageButton) findViewById(R.id.red);
        blue = (ImageButton) findViewById(R.id.blue);
        black = (ImageButton) findViewById(R.id.black);
        canvas.addView(v);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(item.getItemId()){
            case R.id.itemLine:
                curShape = LINE;
                return true;
            case R.id.itemCircle:
                curShape = CIRCLE;
                return true;
            case R.id.itemRect:
                curShape = RECT;
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context) { super(context); }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("CHOI", "move: x = " + event.getX() + " y = " + event.getY());
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }

        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);

            switch (curShape){
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                            + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case RECT:
                    Rect rect = new Rect(startX, startY, stopX, stopY);
                    canvas.drawRect(rect, paint);
                    break;
            }
        }
    }
}