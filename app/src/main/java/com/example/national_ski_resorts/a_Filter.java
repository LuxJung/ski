package com.example.national_ski_resorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.Calendar;
import java.util.Date;

public class a_Filter extends AppCompatActivity {
    final Calendar cal = Calendar.getInstance();
    private Button gyeonggi, gangwon, jeolla, gyeongsang, datePick, timePick,all, reset, search;
    private TextView datePickResult, timePickResult, distance_min, distance_center, distance_max,
            lift_min, lift_center, lift_max, slope_min, slope_center, slope_max;
    private RangeSeekBar lift_rangeSeekbar, distance_rangeSeekbar, slope_rangeSeekbar;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Context context = this;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_filter);
        findId();
        setLocation();
        setDatePicker();
        setTimePicker();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), a_FilterResult.class);
                startActivity(intent);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), a_Main.class);
                startActivity(intent);
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gyeonggi.setBackgroundResource(R.drawable.filter_button_off);
                gangwon.setBackgroundResource(R.drawable.filter_button_off);
                jeolla.setBackgroundResource(R.drawable.filter_button_off);
                gyeongsang.setBackgroundResource(R.drawable.filter_button_off);

                datePick.setBackgroundResource(R.drawable.filter_button_off);
                datePick.setText("날짜 선택하기");
                datePickResult.setText("");

                timePick.setBackgroundResource(R.drawable.filter_button_off);
                timePick.setText("시간 선택하기");
                timePickResult.setText("");

                lift_rangeSeekbar.setSelectedMaxValue(100000);
                lift_rangeSeekbar.setSelectedMinValue(10000);
                lift_rangeSeekbar.setSelected(false);
                lift_rangeSeekbar.resetSelectedValues();
                lift_min.setVisibility(View.INVISIBLE);
                lift_max.setVisibility(View.INVISIBLE);
                lift_center.setText("전체");

                distance_rangeSeekbar.setSelectedMaxValue(330);
                distance_rangeSeekbar.setSelectedMinValue(10);
                distance_rangeSeekbar.setSelected(false);
                distance_rangeSeekbar.resetSelectedValues();
                distance_min.setVisibility(View.INVISIBLE);
                distance_max.setVisibility(View.INVISIBLE);
                distance_center.setText("전체");

                slope_rangeSeekbar.setSelectedMaxValue(6100);
                slope_rangeSeekbar.setSelectedMinValue(100);
                slope_rangeSeekbar.setSelected(false);
                slope_rangeSeekbar.resetSelectedValues();
                slope_min.setVisibility(View.INVISIBLE);
                slope_max.setVisibility(View.INVISIBLE);
                slope_center.setText("전체");
            }
        });

        lift_rangeSeekbar.setRangeValues(10000, 100000, 1000);
        lift_rangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                Number max = bar.getSelectedMaxValue();
                Number min = bar.getSelectedMinValue();
                int mx = (int) max;
                int mn = (int) min;

                lift_min.setText(String.valueOf(mn)+" 원");
                lift_center.setText("~");
                lift_max.setText(String.valueOf(mx)+" 원");
                lift_min.setVisibility(View.VISIBLE);
                lift_max.setVisibility(View.VISIBLE);
            }
        });

        distance_rangeSeekbar.setRangeValues(10, 330, 10);
        distance_rangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                Number max = bar.getSelectedMaxValue();
                Number min = bar.getSelectedMinValue();
                int mx = (int) max;
                int mn = (int) min;

                distance_min.setText(String.valueOf(mn)+" Km");
                distance_center.setText("~");
                distance_max.setText(String.valueOf(mx)+" Km");
                distance_min.setVisibility(View.VISIBLE);
                distance_max.setVisibility(View.VISIBLE);
            }

        });

        slope_rangeSeekbar.setRangeValues(100, 6100, 100);
        slope_rangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                Number max = bar.getSelectedMaxValue();
                Number min = bar.getSelectedMinValue();
                int mx = (int) max;
                int mn = (int) min;

                slope_min.setText(String.valueOf(mn)+" M");
                slope_center.setText("~");
                slope_max.setText(String.valueOf(mx)+" M");
                slope_min.setVisibility(View.VISIBLE);
                slope_max.setVisibility(View.VISIBLE);
            }

        });

        toolbar.setTitle(R.string.Filter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if (id == R.id.all) {
                    Intent intent = new Intent(getApplicationContext(), a_Main.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.location) {
                    Intent intent = new Intent(getApplicationContext(), a_Location.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.filter) {
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.logout) {
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

    }

    private void setLocation() {
        gyeonggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gyeonggi.setBackgroundResource(R.drawable.filter_button_on);
                gangwon.setBackgroundResource(R.drawable.filter_button_off);
                jeolla.setBackgroundResource(R.drawable.filter_button_off);
                gyeongsang.setBackgroundResource(R.drawable.filter_button_off);
            }
        });
        gangwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gyeonggi.setBackgroundResource(R.drawable.filter_button_off);
                gangwon.setBackgroundResource(R.drawable.filter_button_on);
                jeolla.setBackgroundResource(R.drawable.filter_button_off);
                gyeongsang.setBackgroundResource(R.drawable.filter_button_off);
            }
        });
        jeolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gyeonggi.setBackgroundResource(R.drawable.filter_button_off);
                gangwon.setBackgroundResource(R.drawable.filter_button_off);
                jeolla.setBackgroundResource(R.drawable.filter_button_on);
                gyeongsang.setBackgroundResource(R.drawable.filter_button_off);
            }
        });
        gyeongsang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gyeonggi.setBackgroundResource(R.drawable.filter_button_off);
                gangwon.setBackgroundResource(R.drawable.filter_button_off);
                jeolla.setBackgroundResource(R.drawable.filter_button_off);
                gyeongsang.setBackgroundResource(R.drawable.filter_button_on);
            }
        });
    }

    private void setTimePicker() {
        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay<12){
                            timePick.setBackgroundResource(R.drawable.filter_button_on);
                            timePick.setText("시간 선택완료");
                            String am ="오전 ";
                            timePickResult.setText(am+hourOfDay + "시 " + minute + "분");
                            Toast.makeText(getApplicationContext(), am+hourOfDay + "시 " + minute + "분" + "선택 하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                        if(hourOfDay>11){
                            timePick.setBackgroundResource(R.drawable.filter_button_on);
                            timePick.setText("시간 선택완료");
                            String pm ="오후 ";
                            int hourOfDayt = hourOfDay-12;
                            if(hourOfDayt==0){
                                hourOfDayt=12;
                                timePickResult.setText(pm+hourOfDayt + "시 " + minute + "분");
                                Toast.makeText(getApplicationContext(), pm+hourOfDayt + "시 " + minute + "분" + "선택 하였습니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                timePickResult.setText(pm+hourOfDayt + "시 " + minute + "분");
                                Toast.makeText(getApplicationContext(), pm+hourOfDayt + "시 " + minute + "분" + "선택 하였습니다.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                };
                TimePickerDialog dialog = new TimePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,listener, cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false );
                dialog.setTitle("출발 시간");
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.updateTime(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE));
                dialog.show();
            }
        });
    }

    private void setDatePicker() {
        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear+1;
                        datePickResult.setText(year + "년 " + month + "월 " + dayOfMonth + "일");
                        datePick.setBackgroundResource(R.drawable.filter_button_on);
                        datePick.setText("날짜 선택완료");
                        Toast.makeText(getApplicationContext(), year + "년" + month + "월" + dayOfMonth + "일" + "선택 하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                };
                // DatePickerDialog
                DatePickerDialog dialog = new DatePickerDialog(context, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                dialog.getDatePicker().setMinDate(new Date().getTime());
                dialog.show();
            }
        });
    }

    private void findId() {
        toolbar = findViewById(R.id.filter_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.filter_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.filter_nav_view);

        gyeonggi = findViewById(R.id.bt_filter_gyeonggi);
        gangwon = findViewById(R.id.bt_filter_gangwon);
        jeolla = findViewById(R.id.bt_filter_jeolla);
        gyeongsang = findViewById(R.id.bt_filter_gyeongsang);

        datePick = findViewById(R.id.bt_date);
        datePickResult = findViewById(R.id.tv_date);

        timePick = findViewById(R.id.bt_time);
        timePickResult = findViewById(R.id.tv_time);

        distance_rangeSeekbar = findViewById(R.id.distance_rangeSeekbar);
        distance_min = findViewById(R.id.distance_min);
        distance_center = findViewById(R.id.distance_center);
        distance_max = findViewById(R.id.distance_max);

        lift_rangeSeekbar = findViewById(R.id.lift_rangeSeekbar);
        lift_min = findViewById(R.id.lift_min);
        lift_center = findViewById(R.id.lift_center);
        lift_max = findViewById(R.id.lift_max);

        slope_rangeSeekbar = findViewById(R.id.slope_rangeSeekbar);
        slope_min = findViewById(R.id.slope_min);
        slope_center = findViewById(R.id.slope_center);
        slope_max = findViewById(R.id.slope_max);
        all = findViewById(R.id.bt_filter_all);
        reset = findViewById(R.id.bt_reset);
        search = findViewById(R.id.bt_search);
    }

}