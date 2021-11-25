package com.example.national_ski_resorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class a_FilterResult extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_filter_result);
        toolbar = (Toolbar)findViewById(R.id.filterResult_toolbar);
        tel = findViewById(R.id.tel);

        title = findViewById(R.id.name);
        title.setText("조건검색 결과");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullString = tel.getText().toString();
                String[] splitText = fullString.split("-");
                for(int i=0;i<splitText.length;i++){
                    Log.d("TEST", "text = "+splitText[i]);
                }
                Intent tt = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0316441200"));
                startActivity(tt);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}