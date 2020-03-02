package com.manyong.datatransmit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //상수선언
    public static final int REQUEST_JUMIN = 1;

    TextView tv_jumin_no;
    TextView tv_year;
    TextView tv_age;
    TextView tv_tti;
    TextView tv_gender;
    TextView tv_local;
    TextView tv_ganji;
    TextView tv_season;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_jumin_no = (TextView) findViewById(R.id.tv_jumin_no_a);
        tv_year = (TextView) findViewById(R.id.tv_year_a);
        tv_age = (TextView) findViewById(R.id.tv_age_a);
        tv_tti = (TextView) findViewById(R.id.tv_tti_a);
        tv_gender = (TextView) findViewById(R.id.tv_gender_a);
        tv_local = (TextView) findViewById(R.id.tv_local_a);
        tv_ganji = (TextView) findViewById(R.id.tv_ganji_a);
        tv_season = (TextView) findViewById(R.id.tv_season_a);

    }

    //결과화면 띄우기
    public void onClickResult(View view) {

        //입력된 주민번호을 읽어오기
        EditText et_jumin_no = findViewById(R.id.edit_jumin_text);
        String jumin_no = et_jumin_no.getText().toString().trim();


        //값이 비어있는 경우
        if (jumin_no.isEmpty()) {
            et_jumin_no.setText("");//비우기
            //Toast Message 띄우기
            Toast.makeText(this, "주민번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        //명시적 인텐트:호출대상이 명시적으로
        Intent intent = new Intent(this, ResultActivity.class);

        //넘길 데이터 인텐트에 넣는다
        //                     Key        Value
        intent.putExtra("jumin_no", jumin_no);

        startActivity(intent);
    }


    public void onClickResultData(View view) {

        //입력된 주민번호을 읽어오기
        EditText et_jumin_no = findViewById(R.id.edit_jumin_text);
        String jumin_no = et_jumin_no.getText().toString().trim();

        //값이 비어있는 경우
        if (jumin_no.isEmpty()) {
            et_jumin_no.setText("");//비우기
            //Toast Message 띄우기
            Toast.makeText(this, "주민번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        //명시적 인텐트:호출대상이 명시적으로
        Intent intent = new Intent(this, Result2Activity.class);

        //넘길 데이터 인텐트에 넣는다
        //                     Key        Value
        intent.putExtra("jumin_no", jumin_no);
        startActivityForResult(intent, REQUEST_JUMIN);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_JUMIN) {
            if (resultCode == Activity.RESULT_OK) {
                //결과 정상적으로 수신
                HashMap map = (HashMap) data.getSerializableExtra("map");
                
                String jumin_no = (String) map.get("jumin_no");
                int year = (int) map.get("year");
                int age = (int) map.get("age");
                String tti = (String) map.get("tti");
                String season = (String) map.get("season");
                String local = (String) map.get("local");
                String ganji = (String) map.get("ganji");
                String gender = (String) map.get("gender");

                tv_jumin_no.setText("주민번호:" + jumin_no);
                tv_year.setText("출생년도:" + year);
                tv_age.setText("나이:" + age);
                tv_tti.setText("띠:" + tti);
                tv_gender.setText("성별:" + gender);
                tv_local.setText("출생지역:" + local);
                tv_season.setText("출생계절:" + season);
                tv_ganji.setText("10간12지:" + ganji);

            } else {
                Toast.makeText(this, "결과실패!!", Toast.LENGTH_SHORT).show();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
