package com.example.dbsingergroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText edtName, edtNum, edtNameResult, edtNumResult;
    Button btnInit, btnInput, btnLoad, btnModify, btnDelete;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("가수 그룹 관리 DB");

        edtName = (EditText) findViewById(R.id.edname);
        edtNum = (EditText) findViewById(R.id.ednum);
        edtNameResult = (EditText) findViewById(R.id.ednameResult);
        edtNumResult = (EditText) findViewById(R.id.ednumResult);

        btnInit = (Button) findViewById(R.id.btn_Init);
        btnInput = (Button) findViewById(R.id.btn_Input);
        btnLoad = (Button) findViewById(R.id.btn_Load);
        btnModify = (Button) findViewById(R.id.btn_Modify);
        btnDelete = (Button) findViewById(R.id.btn_Delete);

        myHelper = new myDBHelper(this);

        // 초기화
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase(); // 실제 db를 가지고옴
                myHelper.onUpgrade(sqlDB, 1, 2); // 인수는 아무거나 입력해도 됨
                sqlDB.close();
            }
        });

        // 입력
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB= myHelper.getWritableDatabase(); // db를 가지고옴

                String inSql =
                        "INSERT INTO groupTBL VALUES ('"
                        + edtName.getText().toString() +"' ,"
                        + edtNum.getText().toString() + ");";

                // db에 이름과 인원수를 INSERT
                sqlDB.execSQL(inSql);

                // 실행하고 닫음
                sqlDB.close();

                Toast.makeText(getApplicationContext(), "입력",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // 조회
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();

                // Cursor "테이블이 있을 때 raw의 첫 번째 위치를 가지고옴"
                Cursor cursor;
                // rawQuery "(문장)을 실행하겠다"
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String name = "그룹이름" + "\r\n" + "--------" + "\r\n";
                String num = "인원" + "\r\n" + "--------" + "\r\n";

                // moveToNext "다음 행으로 넘어가겠다"
                while(cursor.moveToNext()){
                    name += cursor.getString(0) + "\r\n";
                    num += cursor.getString(1) + "\r\n";
                }

                edtNameResult.setText(name);
                edtNumResult.setText(num);

                cursor.close();
                sqlDB.close();
            }
        });

        // 수정
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();

                String inSql = "UPDATE groupTBL SET gNumber = "+
                        edtNum.getText().toString() + " WHERE gName = '" +
                        edtName.getText().toString() + "';";

                sqlDB.execSQL(inSql);
                sqlDB.close();

                Toast.makeText(getApplicationContext(), "수정",
                        Toast.LENGTH_SHORT).show();

                // 결과 바로 반영
                btnLoad.callOnClick();
            }
        });

        // 삭제
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();

                String inSql = "DELETE FROM groupTBL WHERE gName = '" +
                        edtName.getText().toString() + "';";

                sqlDB.execSQL(inSql);
                sqlDB.close();

                Toast.makeText(getApplicationContext(), "삭제",
                        Toast.LENGTH_SHORT).show();

                // 결과 바로 반영
                btnLoad.callOnClick();
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            // execSQL "SQL을 실행한다", gName -> i: 0 / gNumber -> i: 1
            db.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int old, int cur) {
            // 테이블을 없애고 재생성
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}

