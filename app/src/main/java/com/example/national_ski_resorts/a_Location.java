package com.example.national_ski_resorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class a_Location extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Context context = this;
    private String Name;
    private Button Gyeonggi, Gangwon, Jeolla, Gyeongsang, All, Filter;
    private ImageView GyeonggiBg, GangwonBg, JeollaBg, GyeongsangBg;
    private TextView star1,star2,star3, bears1,bears2,bears3, gon1,gon2,gon3, pine1,pine2,pine3,
            ji1,ji2,ji3, duck1,duck2,duck3, elie1,elie2,elie3, hwi1,hwi2,hwi3,
            yong1,yong2,yong3, al1,al2,al3, vi1,vi2,vi3, wel1,wel2,wel3,
            hi1,hi2,hi3, ork1,ork2,ork3, eden1,eden2,eden3;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_location);
        findId();
        BTclickMove();
        GyeonggiClick();
        GangwonClick();
        JeollaClick();
        GyeongsangClick();
        NameClick();

        toolbar.setTitle(R.string.Location);
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

                if(id == R.id.all){
                    Intent intent = new Intent(getApplicationContext(),a_Main.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(context, title , Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.location){
                    Toast.makeText(context, title , Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.filter){
                    Intent intent = new Intent(getApplicationContext(),a_Filter.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(context, title , Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.logout){
                    Toast.makeText(context, title , Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private void NameClick() {
        //텍스트뷰 클릭 해당 스키장 정보로 이동
        TextView.OnClickListener onClickListener = new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //스타힐리조트
                    case R.id.starhill_1: star(); break;
                    case R.id.starhill_2: star(); break;
                    case R.id.starhill_3: star(); break;
                    //스타힐리조트

                    //베어스타운
                    case R.id.bears_1: bears(); break;
                    case R.id.bears_2: bears(); break;
                    case R.id.bears_3: bears(); break;
                    //베어스타운

                    //곤지암
                    case R.id.gongi_1: gon(); break;
                    case R.id.gongi_2: gon(); break;
                    case R.id.gongi_3: gon(); break;
                    //곤지암

                    //양지파인리조트
                    case R.id.pine_1: pine(); break;
                    case R.id.pine_2: pine(); break;
                    case R.id.pine_3: pine(); break;
                    //양지파인리조트

                    //지산리조트
                    case R.id.jisan_1: ji(); break;
                    case R.id.jisan_2: ji(); break;
                    case R.id.jisan_3: ji(); break;
                    //지산리조트

                    //덕유산리조트
                    case R.id.duck_1: duck(); break;
                    case R.id.duck_2: duck(); break;
                    case R.id.duck_3: duck(); break;
                    //덕유산리조트

                    //오크밸리
                    case R.id.ork_1: ork(); break;
                    case R.id.ork_2: ork(); break;
                    case R.id.ork_3: ork(); break;
                    //오크밸리

                    //엘리시안
                    case R.id.elisian_1: elie(); break;
                    case R.id.elisian_2: elie(); break;
                    case R.id.elisian_3: elie(); break;
                    //엘리시안

                    //비발디파크
                    case R.id.vivaldi_1: vi(); break;
                    case R.id.vivaldi_2: vi(); break;
                    case R.id.vivaldi_3: vi(); break;
                    //비발디파크

                    //웰리힐리파크
                    case R.id.weli_1: wel(); break;
                    case R.id.weli_2: wel(); break;
                    case R.id.weli_3: wel(); break;
                    //웰리힐리파크

                    //휘닉스파크
                    case R.id.hwinix_1: hwi(); break;
                    case R.id.hwinix_2: hwi(); break;
                    case R.id.hwinix_3: hwi(); break;
                    //휘닉스파크

                    //용평리조트
                    case R.id.yongpyung_1: yong(); break;
                    case R.id.yongpyung_2: yong(); break;
                    case R.id.yongpyung_3: yong(); break;
                    //용평리조트

                    //알펜시아리조트
                    case R.id.alpwnsia_1: al(); break;
                    case R.id.alpwnsia_2: al(); break;
                    case R.id.alpwnsia_3: al(); break;
                    //알펜시아리조트

                    //하이원리조트
                    case R.id.hione_1: hi(); break;
                    case R.id.hione_2: hi(); break;
                    case R.id.hione_3: hi(); break;
                    //하이원리조트

                    //에덴벨리리조트
                    case R.id.eden_1: eden(); break;
                    case R.id.eden_2: eden(); break;
                    case R.id.eden_3: eden(); break;
                    //에덴벨리리조트

                }
            }
        };
        star1.setOnClickListener(onClickListener);
        star2.setOnClickListener(onClickListener);
        star3.setOnClickListener(onClickListener);

        bears1.setOnClickListener(onClickListener);
        bears2.setOnClickListener(onClickListener);
        bears3.setOnClickListener(onClickListener);

        gon1.setOnClickListener(onClickListener);
        gon2.setOnClickListener(onClickListener);
        gon3.setOnClickListener(onClickListener);

        pine1.setOnClickListener(onClickListener);
        pine2.setOnClickListener(onClickListener);
        pine3.setOnClickListener(onClickListener);

        ji1.setOnClickListener(onClickListener);
        ji2.setOnClickListener(onClickListener);
        ji3.setOnClickListener(onClickListener);

        duck1.setOnClickListener(onClickListener);
        duck2.setOnClickListener(onClickListener);
        duck3.setOnClickListener(onClickListener);

        ork1.setOnClickListener(onClickListener);
        ork2.setOnClickListener(onClickListener);
        ork3.setOnClickListener(onClickListener);

        elie1.setOnClickListener(onClickListener);
        elie2.setOnClickListener(onClickListener);
        elie3.setOnClickListener(onClickListener);

        vi1.setOnClickListener(onClickListener);
        vi2.setOnClickListener(onClickListener);
        vi3.setOnClickListener(onClickListener);

        wel1.setOnClickListener(onClickListener);
        wel2.setOnClickListener(onClickListener);
        wel3.setOnClickListener(onClickListener);

        hwi1.setOnClickListener(onClickListener);
        hwi2.setOnClickListener(onClickListener);
        hwi3.setOnClickListener(onClickListener);

        yong1.setOnClickListener(onClickListener);
        yong2.setOnClickListener(onClickListener);
        yong3.setOnClickListener(onClickListener);

        al1.setOnClickListener(onClickListener);
        al2.setOnClickListener(onClickListener);
        al3.setOnClickListener(onClickListener);

        hi1.setOnClickListener(onClickListener);
        hi2.setOnClickListener(onClickListener);
        hi3.setOnClickListener(onClickListener);

        eden1.setOnClickListener(onClickListener);
        eden2.setOnClickListener(onClickListener);
        eden3.setOnClickListener(onClickListener);
    }

    private void GyeongsangClick() {
        //경상도 버튼 클릭
        Gyeongsang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gyeonggi.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Gangwon.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Jeolla.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Gyeongsang.setBackgroundColor(getColor(R.color.colorChoiceOn));
                //경기도
                GyeonggiBg.setVisibility(View.GONE);
                star1.setVisibility(View.GONE);
                star2.setVisibility(View.GONE);
                star3.setVisibility(View.GONE);
                bears1.setVisibility(View.GONE);
                bears2.setVisibility(View.GONE);
                bears3.setVisibility(View.GONE);
                ji1.setVisibility(View.GONE);
                ji2.setVisibility(View.GONE);
                ji3.setVisibility(View.GONE);
                gon1.setVisibility(View.GONE);
                gon2.setVisibility(View.GONE);
                gon3.setVisibility(View.GONE);
                pine1.setVisibility(View.GONE);
                pine2.setVisibility(View.GONE);
                pine3.setVisibility(View.GONE);
                //강원도
                GangwonBg.setVisibility(View.GONE);
                ork1.setVisibility(View.GONE);
                ork2.setVisibility(View.GONE);
                ork3.setVisibility(View.GONE);
                wel1.setVisibility(View.GONE);
                wel2.setVisibility(View.GONE);
                wel3.setVisibility(View.GONE);
                hi1.setVisibility(View.GONE);
                hi2.setVisibility(View.GONE);
                hi3.setVisibility(View.GONE);
                elie1.setVisibility(View.GONE);
                elie2.setVisibility(View.GONE);
                elie3.setVisibility(View.GONE);
                hwi1.setVisibility(View.GONE);
                hwi2.setVisibility(View.GONE);
                hwi3.setVisibility(View.GONE);
                yong1.setVisibility(View.GONE);
                yong2.setVisibility(View.GONE);
                yong3.setVisibility(View.GONE);
                al1.setVisibility(View.GONE);
                al2.setVisibility(View.GONE);
                al3.setVisibility(View.GONE);
                vi1.setVisibility(View.GONE);
                vi2.setVisibility(View.GONE);
                vi3.setVisibility(View.GONE);
                //전라도
                JeollaBg.setVisibility(View.GONE);
                duck1.setVisibility(View.GONE);
                duck1.setVisibility(View.GONE);
                duck1.setVisibility(View.GONE);
                //경상도
                GyeongsangBg.setVisibility(View.VISIBLE);
                eden1.setVisibility(View.VISIBLE);
                eden2.setVisibility(View.VISIBLE);
                eden3.setVisibility(View.VISIBLE);
            }
        });
    }

    private void JeollaClick() {
        //전라도 버튼 클릭
        Jeolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gyeonggi.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Gangwon.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Jeolla.setBackgroundColor(getColor(R.color.colorChoiceOn));
                Gyeongsang.setBackgroundColor(getColor(R.color.colorChoiceOff));
                //경기도
                GyeonggiBg.setVisibility(View.GONE);
                star1.setVisibility(View.GONE);
                star2.setVisibility(View.GONE);
                star3.setVisibility(View.GONE);
                bears1.setVisibility(View.GONE);
                bears2.setVisibility(View.GONE);
                bears3.setVisibility(View.GONE);
                ji1.setVisibility(View.GONE);
                ji2.setVisibility(View.GONE);
                ji3.setVisibility(View.GONE);
                gon1.setVisibility(View.GONE);
                gon2.setVisibility(View.GONE);
                gon3.setVisibility(View.GONE);
                pine1.setVisibility(View.GONE);
                pine2.setVisibility(View.GONE);
                pine3.setVisibility(View.GONE);
                //강원도
                GangwonBg.setVisibility(View.GONE);
                ork1.setVisibility(View.GONE);
                ork2.setVisibility(View.GONE);
                ork3.setVisibility(View.GONE);
                wel1.setVisibility(View.GONE);
                wel2.setVisibility(View.GONE);
                wel3.setVisibility(View.GONE);
                hi1.setVisibility(View.GONE);
                hi2.setVisibility(View.GONE);
                hi3.setVisibility(View.GONE);
                elie1.setVisibility(View.GONE);
                elie2.setVisibility(View.GONE);
                elie3.setVisibility(View.GONE);
                hwi1.setVisibility(View.GONE);
                hwi2.setVisibility(View.GONE);
                hwi3.setVisibility(View.GONE);
                yong1.setVisibility(View.GONE);
                yong2.setVisibility(View.GONE);
                yong3.setVisibility(View.GONE);
                al1.setVisibility(View.GONE);
                al2.setVisibility(View.GONE);
                al3.setVisibility(View.GONE);
                vi1.setVisibility(View.GONE);
                vi2.setVisibility(View.GONE);
                vi3.setVisibility(View.GONE);
                //전라도
                JeollaBg.setVisibility(View.VISIBLE);
                duck1.setVisibility(View.VISIBLE);
                duck2.setVisibility(View.VISIBLE);
                duck3.setVisibility(View.VISIBLE);
                //경상도
                GyeongsangBg.setVisibility(View.GONE);
                eden1.setVisibility(View.GONE);
                eden2.setVisibility(View.GONE);
                eden3.setVisibility(View.GONE);
            }
        });
    }

    private void GangwonClick() {
        //강원도 버튼 클릭
        Gangwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gyeonggi.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Gangwon.setBackgroundColor(getColor(R.color.colorChoiceOn));
                Jeolla.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Gyeongsang.setBackgroundColor(getColor(R.color.colorChoiceOff));
                //경기도
                GyeonggiBg.setVisibility(View.GONE);
                star1.setVisibility(View.GONE);
                star2.setVisibility(View.GONE);
                star3.setVisibility(View.GONE);
                bears1.setVisibility(View.GONE);
                bears2.setVisibility(View.GONE);
                bears3.setVisibility(View.GONE);
                ji1.setVisibility(View.GONE);
                ji2.setVisibility(View.GONE);
                ji3.setVisibility(View.GONE);
                gon1.setVisibility(View.GONE);
                gon2.setVisibility(View.GONE);
                gon3.setVisibility(View.GONE);
                pine1.setVisibility(View.GONE);
                pine2.setVisibility(View.GONE);
                pine3.setVisibility(View.GONE);
                //강원도
                GangwonBg.setVisibility(View.VISIBLE);
                ork1.setVisibility(View.VISIBLE);
                ork2.setVisibility(View.VISIBLE);
                ork3.setVisibility(View.VISIBLE);
                wel1.setVisibility(View.VISIBLE);
                wel2.setVisibility(View.VISIBLE);
                wel3.setVisibility(View.VISIBLE);
                hi1.setVisibility(View.VISIBLE);
                hi2.setVisibility(View.VISIBLE);
                hi3.setVisibility(View.VISIBLE);
                elie1.setVisibility(View.VISIBLE);
                elie2.setVisibility(View.VISIBLE);
                elie3.setVisibility(View.VISIBLE);
                hwi1.setVisibility(View.VISIBLE);
                hwi2.setVisibility(View.VISIBLE);
                hwi3.setVisibility(View.VISIBLE);
                yong1.setVisibility(View.VISIBLE);
                yong2.setVisibility(View.VISIBLE);
                yong3.setVisibility(View.VISIBLE);
                al1.setVisibility(View.VISIBLE);
                al2.setVisibility(View.VISIBLE);
                al3.setVisibility(View.VISIBLE);
                vi1.setVisibility(View.VISIBLE);
                vi2.setVisibility(View.VISIBLE);
                vi3.setVisibility(View.VISIBLE);
                //전라도
                JeollaBg.setVisibility(View.GONE);
                duck1.setVisibility(View.GONE);
                duck2.setVisibility(View.GONE);
                duck3.setVisibility(View.GONE);
                //경상도
                GyeongsangBg.setVisibility(View.GONE);
                eden1.setVisibility(View.GONE);
                eden2.setVisibility(View.GONE);
                eden3.setVisibility(View.GONE);
            }
        });
    }

    private void GyeonggiClick() {
        //경기도 버튼 클릭
        Gyeonggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gyeonggi.setBackgroundColor(getColor(R.color.colorChoiceOn));
                Gangwon.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Jeolla.setBackgroundColor(getColor(R.color.colorChoiceOff));
                Gyeongsang.setBackgroundColor(getColor(R.color.colorChoiceOff));
                //경기도
                GyeonggiBg.setVisibility(View.VISIBLE);
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                bears1.setVisibility(View.VISIBLE);
                bears2.setVisibility(View.VISIBLE);
                bears3.setVisibility(View.VISIBLE);
                ji1.setVisibility(View.VISIBLE);
                ji2.setVisibility(View.VISIBLE);
                ji3.setVisibility(View.VISIBLE);
                gon1.setVisibility(View.VISIBLE);
                gon2.setVisibility(View.VISIBLE);
                gon3.setVisibility(View.VISIBLE);
                pine1.setVisibility(View.VISIBLE);
                pine2.setVisibility(View.VISIBLE);
                pine3.setVisibility(View.VISIBLE);
                //강원도
                GangwonBg.setVisibility(View.GONE);
                ork1.setVisibility(View.GONE);
                ork2.setVisibility(View.GONE);
                ork3.setVisibility(View.GONE);
                wel1.setVisibility(View.GONE);
                wel2.setVisibility(View.GONE);
                wel3.setVisibility(View.GONE);
                hi1.setVisibility(View.GONE);
                hi2.setVisibility(View.GONE);
                hi3.setVisibility(View.GONE);
                elie1.setVisibility(View.GONE);
                elie2.setVisibility(View.GONE);
                elie3.setVisibility(View.GONE);
                hwi1.setVisibility(View.GONE);
                hwi2.setVisibility(View.GONE);
                hwi3.setVisibility(View.GONE);
                yong1.setVisibility(View.GONE);
                yong2.setVisibility(View.GONE);
                yong3.setVisibility(View.GONE);
                al1.setVisibility(View.GONE);
                al2.setVisibility(View.GONE);
                al3.setVisibility(View.GONE);
                vi1.setVisibility(View.GONE);
                vi2.setVisibility(View.GONE);
                vi3.setVisibility(View.GONE);
                //전라도
                JeollaBg.setVisibility(View.GONE);
                duck1.setVisibility(View.GONE);
                duck2.setVisibility(View.GONE);
                duck3.setVisibility(View.GONE);
                //경상도
                GyeongsangBg.setVisibility(View.GONE);
                eden1.setVisibility(View.GONE);
                eden2.setVisibility(View.GONE);
                eden3.setVisibility(View.GONE);
            }
        });
    }

    private void BTclickMove() {
        //전국 버튼클릭 페이지 이동
        All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),a_Main.class);
                startActivity(intent);
                finish();
            }
        });
        //필터검색 버튼클릭 필터 페이지 이동
        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),a_Filter.class);
                startActivity(intent);
            }
        });
    }

    private void findId() {
        toolbar = (Toolbar)findViewById(R.id.loca_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.Loca_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.Loca_nav_view);
        //버튼들
        Gyeonggi = (Button) findViewById(R.id.bt_gyeonggi);
        Gangwon = (Button) findViewById(R.id.bt_gangwon);
        Jeolla = (Button) findViewById(R.id.bt_jeolla);
        Gyeongsang = (Button) findViewById(R.id.bt_gyeongsang);
        All = (Button) findViewById(R.id.bt_all);
        Filter = (Button) findViewById(R.id.bt_locafilter);
        //이미지뷰
        GyeonggiBg = findViewById(R.id.iv_gyeonggibg);
        GangwonBg = findViewById(R.id.iv_gangwonbg);
        JeollaBg = findViewById(R.id.iv_jeollabg);
        GyeongsangBg = findViewById(R.id.iv_gyeongsangbg);
        //텍스트뷰
        star1 = findViewById(R.id.starhill_1);
        star2 = findViewById(R.id.starhill_2);
        star3 = findViewById(R.id.starhill_3);
        bears1 = findViewById(R.id.bears_1);
        bears2 = findViewById(R.id.bears_2);
        bears3 = findViewById(R.id.bears_3);
        gon1 = findViewById(R.id.gongi_1);
        gon2 = findViewById(R.id.gongi_2);
        gon3 = findViewById(R.id.gongi_3);
        pine1 = findViewById(R.id.pine_1);
        pine2 = findViewById(R.id.pine_2);
        pine3 = findViewById(R.id.pine_3);
        ji1 = findViewById(R.id.jisan_1);
        ji2 = findViewById(R.id.jisan_2);
        ji3 = findViewById(R.id.jisan_3);
        duck1 = findViewById(R.id.duck_1);
        duck2 = findViewById(R.id.duck_2);
        duck3 = findViewById(R.id.duck_3);
        elie1 = findViewById(R.id.elisian_1);
        elie2 = findViewById(R.id.elisian_2);
        elie3 = findViewById(R.id.elisian_3);
        hwi1 = findViewById(R.id.hwinix_1);
        hwi2 = findViewById(R.id.hwinix_2);
        hwi3 = findViewById(R.id.hwinix_3);
        yong1 = findViewById(R.id.yongpyung_1);
        yong2 = findViewById(R.id.yongpyung_2);
        yong3 = findViewById(R.id.yongpyung_3);
        al1 = findViewById(R.id.alpwnsia_1);
        al2 = findViewById(R.id.alpwnsia_2);
        al3 = findViewById(R.id.alpwnsia_3);
        vi1 = findViewById(R.id.vivaldi_1);
        vi2 = findViewById(R.id.vivaldi_2);
        vi3 = findViewById(R.id.vivaldi_3);
        wel1 = findViewById(R.id.weli_1);
        wel2 = findViewById(R.id.weli_2);
        wel3 = findViewById(R.id.weli_3);
        hi1 = findViewById(R.id.hione_1);
        hi2 = findViewById(R.id.hione_2);
        hi3 = findViewById(R.id.hione_3);
        ork1 = findViewById(R.id.ork_1);
        ork2 = findViewById(R.id.ork_2);
        ork3 = findViewById(R.id.ork_3);
        eden1 = findViewById(R.id.eden_1);
        eden2 = findViewById(R.id.eden_2);
        eden3 = findViewById(R.id.eden_3);

    }

    private void star() {
        Name = star2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void bears() {
        Name = bears2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void gon() {
        Name = gon2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void pine() {
        Name = pine2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void ji() {
        Name = ji2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void duck() {
        Name = duck2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void ork() {
        Name = ork2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void vi() {
        Name = vi2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void wel() {
        Name = wel2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void elie() {
        Name = elie2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void hwi() {
        Name = hwi2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void yong() {
        Name = yong2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void hi() {
        Name = hi2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void al() {
        Name = al2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
    private void eden() {
        Name = eden2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    } //ㅇㅋ
}