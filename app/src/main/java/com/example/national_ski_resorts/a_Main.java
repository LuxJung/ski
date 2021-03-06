package com.example.national_ski_resorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class a_Main extends AppCompatActivity implements OnMapReadyCallback {//, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private LinearLayout LoginNo, LoginYes;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Button location, filter;
    private ImageView backIMG;
    private TextView star1, star2, star3, bears1, bears2, bears3, gon1, gon2, gon3, pine1, pine2, pine3,
            ji1, ji2, ji3, duck1, duck2, duck3, elie1, elie2, elie3, hwi1, hwi2, hwi3,
            yong1, yong2, yong3, al1, al2, al3, vi1, vi2, vi3, wel1, wel2, wel3,
            hi1, hi2, hi3, ork1, ork2, ork3, eden1, eden2, eden3;
    private String Name;
    private int star, star_ad, bears, bears_ad, gon, gon_ad, pine, pine_ad, ji, ji_ad, duck, duck_ad, elie, elie_ad, hwi, hwi_ad,
            yong, yong_ad, al, al_ad, vi, vi_ad, wel, wel_ad, ork, ork_ad, hi, hi_ad, eden, eden_ad;
    private Boolean LoginState = false;
    private Switch mSwitch;
    private Context context = this;
    private TextView ProfileEmail, ProfileName;
    private ImageView ProfileImg;
    private GoogleSignInAccount mGoogleSignInAccount;
    private GoogleSignInClient mGoogleSignInClient;
    public static final String GOOGLE_ACCOUNT = "google_account";
    private Handler mHandler = null;
    private FirebaseAuth mAuth;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override// ?????? ?????? ????????? ?????? ????????? ???
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // ?????? ?????? ?????? ????????? ???
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_main);
        findId();
        BTclickMove();
        NameClick();
        //LoginState = getIntent().getBooleanExtra("loginyes",false);

        View header = navigationView.getHeaderView(0);
        LoginNo = header.findViewById(R.id.login_no);
        LoginYes = header.findViewById(R.id.logon_yes);
        ProfileEmail = header.findViewById(R.id.profileEmail);
        ProfileName = header.findViewById(R.id.profileName);
        ProfileImg = header.findViewById(R.id.profileImg);
        mAuth = FirebaseAuth.getInstance();

        //?????????????????? ?????? UI ????????? ?????? ?????????
        mHandler = new Handler();
        //????????? ????????? ?????? ?????????. (?????? ?????? ?????????)
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // UI ?????? ?????? X
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // UI ?????? ??????
                        if (mAuth.getCurrentUser() == null) {
                            Log.e("mAuth null", "null");
                            LoginState = true;
                            //????????? ?????? ??????
                            Log.e(LoginState.toString(), "LoginState.toString()");
                            LoginNo.setVisibility(View.VISIBLE);
                            LoginYes.setVisibility(View.GONE);
                        } else {
                            Log.e("mAuth not null", "not null");
                            LoginState = false;
                            //????????? ?????? ?????? ?????? ???????????? ????????? ???
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.e(LoginState.toString(), "LoginState.toString()");
                            LoginNo.setVisibility(View.GONE);
                            LoginYes.setVisibility(View.VISIBLE);
                            Picasso.get().load(user.getPhotoUrl()).centerInside().fit().into(ProfileImg);
                            ProfileName.setText(user.getDisplayName());
                            ProfileEmail.setText(user.getEmail());
                        }
                    }
                });
            }
        });
        t.start();


        //?????? ?????????
        toolbar.setTitle(R.string.myAppName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);

        TextView LoginGo = header.findViewById(R.id.header_login);
        ImageView ProfileImg = header.findViewById(R.id.profileImg);
        LoginGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "??????????????????", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), z_Login.class);
                startActivity(intent);
            }
        });
        //?????? ??????????????? ???????????????
        UserSetting();


        //?????????????????? ????????? ?????? ?????????
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();


                if (id == R.id.all) {
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.location) {
                    Intent intent = new Intent(getApplicationContext(), a_Location.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.filter) {
                    Intent intent = new Intent(getApplicationContext(), a_Filter.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.logout) {
                    Intent intent = new Intent(getApplicationContext(), a_Test.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
        mSwitch.setChecked(false);
        mSwitch.setOnCheckedChangeListener(new visibilitySwitchListener());

        mapFragment.getMapAsync(this);


    }

    //?????? ?????? ??????
    private void UserSetting() {
        ProfileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_Main.this, a_UserProfile.class);
                startActivity(intent);
                Toast.makeText(context, "?????? ?????????", Toast.LENGTH_SHORT).show();
            }
        });
        ProfileEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_Main.this, a_UserProfile.class);
                startActivity(intent);
                Toast.makeText(context, "????????? ?????????", Toast.LENGTH_SHORT).show();
            }
        });
        //?????? ????????? ????????? ????????? ??????
        ProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_Main.this, a_UserProfile.class);
                startActivity(intent);
                Toast.makeText(context, "???????????? ?????????", Toast.LENGTH_SHORT).show();
            }
        });
    }

        //?????????
    //????????? ??????
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        //mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
        onAddMarker();

        LatLng Center = new LatLng(36.771468, 127.955656);
        LatLngBounds adelaideBounds = new LatLngBounds(
                new LatLng(34.503003, 126.260760), // ??????SW bounds
                new LatLng(38.382058, 129.780065)  // ??????NE bounds
        );
        mMap.setMinZoomPreference(7.5f);
        mMap.setLatLngBoundsForCameraTarget(adelaideBounds);
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(adelaideBounds.getCenter(), (float) 7.5));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Center, (float) 7.5));
        mMap.setOnMarkerClickListener(markerClickListener);
        mMap.setOnInfoWindowClickListener(infoWindowClickListener);
    }

    GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            double jisanL = 37.217314, jisanH = 127.344085,//???????????????
                    bearstownL = 37.796476, bearstownH = 127.247714,//???????????????
                    starhillL = 37.662935, starhillH = 127.269144,//??????????????????
                    gonjiamL = 37.337965, gonjiamH = 127.295152,//??????????????????
                    pineL = 37.213229, pineH = 127.295120,//?????????????????????
                    duckyousanL = 35.891784, duckyousanH = 127.733567,//??????????????????
                    edenveleyL = 35.429238, edenveleyH = 128.984500,//?????????????????????
                    elisianL = 37.822262, elisianH = 127.589811,//?????????????????????
                    orkvelyL = 37.408687, orkvelyH = 127.807632247714,//????????????
                    welihilyL = 37.491293, welihilyH = 128.250558,//??????????????????
                    hioneL = 37.206808, hioneH = 128.837993,//??????????????????
                    vivaldiL = 37.643775, vivaldiH = 127.683737,//???????????????
                    hweinixL = 37.579269, hweinixH = 128.331566,//???????????????
                    alpensiaL = 37.654420, alpensiaH = 128.671732,//?????????????????????
                    yongpyungL = 37.644480, yongpyungH = 128.679917;//???????????????

            switch (marker.getTitle()) {
                case "??????????????????":
                    LatLng STAR = new LatLng(starhillL, starhillH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STAR, 15));
                    break;
                case "???????????????":
                    LatLng BEARS = new LatLng(bearstownL, bearstownH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BEARS, 15));
                    break;
                case "??????????????????":
                    LatLng GON = new LatLng(gonjiamL, gonjiamH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GON, 15));
                    break;
                case "?????????????????????":
                    LatLng PINE = new LatLng(pineL, pineH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PINE, 15));
                    break;
                case "???????????????":
                    LatLng JI = new LatLng(jisanL, jisanH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(JI, 15));
                    break;
                case "??????????????????":
                    LatLng DUCK = new LatLng(duckyousanL, duckyousanH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DUCK, 15));
                    break;
                case "??????????????????":
                    LatLng ORK = new LatLng(orkvelyL, orkvelyH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ORK, 15));
                    break;
                case "???????????????":
                    LatLng VI = new LatLng(vivaldiL, vivaldiH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(VI, 15));
                    break;
                case "??????????????????":
                    LatLng WEL = new LatLng(welihilyL, welihilyH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(WEL, 15));
                    break;
                case "?????????????????????":
                    LatLng ELI = new LatLng(elisianL, elisianH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ELI, 15));
                    break;
                case "???????????????":
                    LatLng HWE = new LatLng(hweinixL, hweinixH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HWE, 15));
                    break;
                case "???????????????":
                    LatLng YONG = new LatLng(yongpyungL, yongpyungH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(YONG, 15));
                    break;
                case "??????????????????":
                    LatLng HI = new LatLng(hioneL, hioneH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HI, 15));
                    break;
                case "?????????????????????":
                    LatLng AL = new LatLng(alpensiaL, alpensiaH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AL, 15));
                    break;
                case "?????????????????????":
                    LatLng EDEN = new LatLng(edenveleyL, edenveleyH);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EDEN, 15));
                    break;
            }
            return false;
        }
    };
    //????????? ????????? ?????? ?????????
    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            switch (marker.getTitle()) {
                case "??????????????????":
                    star();
                    break;
                case "???????????????":
                    bears();
                    break;
                case "??????????????????":
                    gon();
                    break;
                case "?????????????????????":
                    pine();
                    break;
                case "???????????????":
                    ji();
                    break;
                case "??????????????????":
                    duck();
                    break;
                case "??????????????????":
                    ork();
                    break;
                case "???????????????":
                    vi();
                    break;
                case "??????????????????":
                    wel();
                    break;
                case "?????????????????????":
                    elie();
                    break;
                case "???????????????":
                    hwi();
                    break;
                case "???????????????":
                    yong();
                    break;
                case "??????????????????":
                    hi();
                    break;
                case "?????????????????????":
                    al();
                    break;
                case "?????????????????????":
                    eden();
                    break;
            }
        }
    };


    //@Override
    /*public boolean onMarkerClick(Marker marker) {
        if(marker.getTitle().equals("??????????????????")){
            LatLng STAR = new LatLng(starhillL, starhillH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STAR, (float) 13));
        }else if(marker.getTitle().equals("???????????????")){
            bears();
        }else if(marker.getTitle().equals("??????????????????")){
            gon();
        }else if(marker.getTitle().equals("?????????????????????")){
            pine();
        }else if(marker.getTitle().equals("???????????????")){
            ji();
        }else if(marker.getTitle().equals("??????????????????")){
            duck();
        }else if(marker.getTitle().equals("??????????????????")){
            ork();
        }else if(marker.getTitle().equals("???????????????")){
            vi();
        }else if(marker.getTitle().equals("??????????????????")){
            wel();
        }else if(marker.getTitle().equals("?????????????????????")){
            elie();
        }else if(marker.getTitle().equals("???????????????")){
            hwi();
        }else if(marker.getTitle().equals("???????????????")){
            yong();
        }else if(marker.getTitle().equals("??????????????????")){
            hi();
        }else if(marker.getTitle().equals("?????????????????????")){
            al();
        }else if(marker.getTitle().equals("?????????????????????")){
            eden();
        }
        return true;
    }*/

    //????????? ????????? ?????????
    class visibilitySwitchListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                mapFragment.getView().setVisibility(View.GONE);
                backIMG.setVisibility(View.VISIBLE);
                //??????????????????
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                //??????????????????

                //???????????????
                bears1.setVisibility(View.VISIBLE);
                bears2.setVisibility(View.VISIBLE);
                bears3.setVisibility(View.VISIBLE);
                //???????????????

                //?????????
                gon1.setVisibility(View.VISIBLE);
                gon2.setVisibility(View.VISIBLE);
                gon3.setVisibility(View.VISIBLE);
                //?????????

                //?????????????????????
                pine1.setVisibility(View.VISIBLE);
                pine2.setVisibility(View.VISIBLE);
                pine3.setVisibility(View.VISIBLE);
                //?????????????????????

                //???????????????
                ji1.setVisibility(View.VISIBLE);
                ji2.setVisibility(View.VISIBLE);
                ji3.setVisibility(View.VISIBLE);
                //???????????????

                //??????????????????
                duck1.setVisibility(View.VISIBLE);
                duck2.setVisibility(View.VISIBLE);
                duck3.setVisibility(View.VISIBLE);
                //??????????????????

                //????????????
                ork1.setVisibility(View.VISIBLE);
                ork2.setVisibility(View.VISIBLE);
                ork3.setVisibility(View.VISIBLE);
                //????????????

                //????????????
                elie1.setVisibility(View.VISIBLE);
                elie2.setVisibility(View.VISIBLE);
                elie3.setVisibility(View.VISIBLE);
                //????????????

                //???????????????
                vi1.setVisibility(View.VISIBLE);
                vi2.setVisibility(View.VISIBLE);
                vi3.setVisibility(View.VISIBLE);
                //???????????????

                //??????????????????
                wel1.setVisibility(View.VISIBLE);
                wel2.setVisibility(View.VISIBLE);
                wel3.setVisibility(View.VISIBLE);
                //??????????????????

                //???????????????
                hwi1.setVisibility(View.VISIBLE);
                hwi2.setVisibility(View.VISIBLE);
                hwi3.setVisibility(View.VISIBLE);
                //???????????????

                //???????????????
                yong1.setVisibility(View.VISIBLE);
                yong2.setVisibility(View.VISIBLE);
                yong3.setVisibility(View.VISIBLE);
                //???????????????

                //?????????????????????
                al1.setVisibility(View.VISIBLE);
                al2.setVisibility(View.VISIBLE);
                al3.setVisibility(View.VISIBLE);
                //?????????????????????

                //??????????????????
                hi1.setVisibility(View.VISIBLE);
                hi2.setVisibility(View.VISIBLE);
                hi3.setVisibility(View.VISIBLE);
                //??????????????????

                //?????????????????????
                eden1.setVisibility(View.VISIBLE);
                eden2.setVisibility(View.VISIBLE);
                eden3.setVisibility(View.VISIBLE);
                //?????????????????????
            } else {
                mapFragment.getView().setVisibility(View.VISIBLE);
                backIMG.setVisibility(View.GONE);
                //??????????????????
                star1.setVisibility(View.GONE);
                star2.setVisibility(View.GONE);
                star3.setVisibility(View.GONE);
                //??????????????????

                //???????????????
                bears1.setVisibility(View.GONE);
                bears2.setVisibility(View.GONE);
                bears3.setVisibility(View.GONE);
                //???????????????

                //?????????
                gon1.setVisibility(View.GONE);
                gon2.setVisibility(View.GONE);
                gon3.setVisibility(View.GONE);
                //?????????

                //?????????????????????
                pine1.setVisibility(View.GONE);
                pine2.setVisibility(View.GONE);
                pine3.setVisibility(View.GONE);
                //?????????????????????

                //???????????????
                ji1.setVisibility(View.GONE);
                ji2.setVisibility(View.GONE);
                ji3.setVisibility(View.GONE);
                //???????????????

                //??????????????????
                duck1.setVisibility(View.GONE);
                duck2.setVisibility(View.GONE);
                duck3.setVisibility(View.GONE);
                //??????????????????

                //????????????
                ork1.setVisibility(View.GONE);
                ork2.setVisibility(View.GONE);
                ork3.setVisibility(View.GONE);
                //????????????

                //????????????
                elie1.setVisibility(View.GONE);
                elie2.setVisibility(View.GONE);
                elie3.setVisibility(View.GONE);
                //????????????

                //???????????????
                vi1.setVisibility(View.GONE);
                vi2.setVisibility(View.GONE);
                vi3.setVisibility(View.GONE);
                //???????????????

                //??????????????????
                wel1.setVisibility(View.GONE);
                wel2.setVisibility(View.GONE);
                wel3.setVisibility(View.GONE);
                //??????????????????

                //???????????????
                hwi1.setVisibility(View.GONE);
                hwi2.setVisibility(View.GONE);
                hwi3.setVisibility(View.GONE);
                //???????????????

                //???????????????
                yong1.setVisibility(View.GONE);
                yong2.setVisibility(View.GONE);
                yong3.setVisibility(View.GONE);
                //???????????????

                //?????????????????????
                al1.setVisibility(View.GONE);
                al2.setVisibility(View.GONE);
                al3.setVisibility(View.GONE);
                //?????????????????????

                //??????????????????
                hi1.setVisibility(View.GONE);
                hi2.setVisibility(View.GONE);
                hi3.setVisibility(View.GONE);
                //??????????????????

                //?????????????????????
                eden1.setVisibility(View.GONE);
                eden2.setVisibility(View.GONE);
                eden3.setVisibility(View.GONE);
                //?????????????????????
            }
        }
    }

    //????????? ?????? ????????????
    public void onAddMarker() {
        double jisanL = 37.217314, jisanH = 127.344085,//???????????????
                bearstownL = 37.796476, bearstownH = 127.247714,//???????????????
                starhillL = 37.662935, starhillH = 127.269144,//??????????????????
                gonjiamL = 37.337965, gonjiamH = 127.295152,//??????????????????
                pineL = 37.213229, pineH = 127.295120,//?????????????????????
                duckyousanL = 35.891784, duckyousanH = 127.733567,//??????????????????
                edenveleyL = 35.429238, edenveleyH = 128.984500,//?????????????????????
                elisianL = 37.822262, elisianH = 127.589811,//?????????????????????
                orkvelyL = 37.408687, orkvelyH = 127.807632247714,//????????????
                welihilyL = 37.491293, welihilyH = 128.250558,//??????????????????
                hioneL = 37.206808, hioneH = 128.837993,//??????????????????
                vivaldiL = 37.643775, vivaldiH = 127.683737,//???????????????
                hweinixL = 37.579269, hweinixH = 128.331566,//???????????????
                alpensiaL = 37.654420, alpensiaH = 128.671732,//?????????????????????
                yongpyungL = 37.644480, yongpyungH = 128.679917;//???????????????

        LatLng BEARS = new LatLng(bearstownL, bearstownH);
        MarkerOptions markerBEARS = new MarkerOptions();
        markerBEARS.position(BEARS);
        markerBEARS.title("???????????????");
        markerBEARS.snippet("????????? ????????? ????????? ????????? 305");

        LatLng STAR = new LatLng(starhillL, starhillH);
        MarkerOptions markerSTAR = new MarkerOptions();
        markerSTAR.position(STAR);
        markerSTAR.title("??????????????????");
        markerSTAR.snippet("????????? ???????????? ????????? ????????? 96");

        LatLng GON = new LatLng(gonjiamL, gonjiamH);
        MarkerOptions markerGON = new MarkerOptions();
        markerGON.position(GON);
        markerGON.title("??????????????????");
        markerGON.snippet("????????? ????????? ????????? ???????????? 278");

        LatLng PINE = new LatLng(pineL, pineH);
        MarkerOptions markerPINE = new MarkerOptions();
        markerPINE.position(PINE);
        markerPINE.title("?????????????????????");
        markerPINE.snippet("????????? ????????? ????????? ????????? ????????? 34-1");

        LatLng JI = new LatLng(jisanL, jisanH);
        MarkerOptions markerJI = new MarkerOptions();
        markerJI.position(JI);
        markerJI.title("???????????????");
        markerJI.snippet("????????? ????????? ????????? ????????? 357-3");

        LatLng DUCK = new LatLng(duckyousanL, duckyousanH);
        MarkerOptions markerDUCK = new MarkerOptions();
        markerDUCK.position(DUCK);
        markerDUCK.title("??????????????????");
        markerDUCK.snippet("???????????? ????????? ????????? ????????? ???43-15");

        LatLng ORK = new LatLng(orkvelyL, orkvelyH);
        MarkerOptions markerORK = new MarkerOptions();
        markerORK.position(ORK);
        markerORK.title("??????????????????");
        markerORK.snippet("????????? ????????? ????????? ????????? ???1-12");

        LatLng VI = new LatLng(vivaldiL, vivaldiH);
        MarkerOptions markerVI = new MarkerOptions();
        markerVI.position(VI);
        markerVI.title("???????????????");
        markerVI.snippet("????????? ????????? ?????? ???????????? 262");

        LatLng WEL = new LatLng(welihilyL, welihilyH);
        MarkerOptions markerWEL = new MarkerOptions();
        markerWEL.position(WEL);
        markerWEL.title("??????????????????");
        markerWEL.snippet("????????? ????????? ????????? ????????? 204");

        LatLng ELI = new LatLng(elisianL, elisianH);
        MarkerOptions markerELI = new MarkerOptions();
        markerELI.position(ELI);
        markerELI.title("?????????????????????");
        markerELI.snippet("????????? ????????? ????????? ??????????????? 688");

        LatLng HWE = new LatLng(hweinixL, hweinixH);
        MarkerOptions markerHWE = new MarkerOptions();
        markerHWE.position(HWE);
        markerHWE.title("???????????????");
        markerHWE.snippet("????????? ????????? ????????? ????????? 946-14");

        LatLng YONG = new LatLng(yongpyungL, yongpyungH);
        MarkerOptions markeYONG = new MarkerOptions();
        markeYONG.position(YONG);
        markeYONG.title("???????????????");
        markeYONG.snippet("????????? ????????? ???????????? ????????? 99-5");

        LatLng HI = new LatLng(hioneL, hioneH);
        MarkerOptions markerHI = new MarkerOptions();
        markerHI.position(HI);
        markerHI.title("??????????????????");
        markerHI.snippet("????????? ????????? ????????? ?????????");

        LatLng AL = new LatLng(alpensiaL, alpensiaH);
        MarkerOptions markerAL = new MarkerOptions();
        markerAL.position(AL);
        markerAL.title("?????????????????????");
        markerAL.snippet("????????? ????????? ????????? ????????? 204");

        LatLng EDEN = new LatLng(edenveleyL, edenveleyH);
        MarkerOptions markerEDEN = new MarkerOptions();
        markerEDEN.position(EDEN);
        markerEDEN.title("?????????????????????");
        markerEDEN.snippet("???????????? ????????? ????????? ????????? 1206");

        mMap.addMarker(markerBEARS);
        mMap.addMarker(markerSTAR);
        mMap.addMarker(markerGON);
        mMap.addMarker(markerPINE);
        mMap.addMarker(markerJI);
        mMap.addMarker(markerDUCK);
        mMap.addMarker(markerORK);
        mMap.addMarker(markerVI);
        mMap.addMarker(markerWEL);
        mMap.addMarker(markerELI);
        mMap.addMarker(markerHWE);
        mMap.addMarker(markeYONG);
        mMap.addMarker(markerHI);
        mMap.addMarker(markerAL);
        mMap.addMarker(markerEDEN);
    }

    //??????????????? ????????????
    private void BTclickMove() {
        //????????? ???????????? ????????? ??????
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), a_Location.class);
                startActivity(intent);
                finish();
            }
        });

        //???????????? ???????????? ?????? ????????? ??????
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), a_Filter.class);
                startActivity(intent);
            }
        });
    }

    private void findId() {
        toolbar = findViewById(R.id.main_toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mSwitch = findViewById(R.id.switch_main);

        location = findViewById(R.id.bt_loca);
        filter = findViewById(R.id.bt_filter);
        backIMG = findViewById(R.id.iv_all);
        star1 = findViewById(R.id.Mstarhill_1);
        star2 = findViewById(R.id.Mstarhill_2);
        star3 = findViewById(R.id.Mstarhill_3);
        bears1 = findViewById(R.id.Mbears_1);
        bears2 = findViewById(R.id.Mbears_2);
        bears3 = findViewById(R.id.Mbears_3);
        gon1 = findViewById(R.id.Mgonjiam_1);
        gon2 = findViewById(R.id.Mgonjiam_2);
        gon3 = findViewById(R.id.Mgonjiam_3);
        pine1 = findViewById(R.id.Mpine_1);
        pine2 = findViewById(R.id.Mpine_2);
        pine3 = findViewById(R.id.Mpine_3);
        ji1 = findViewById(R.id.Mjisan_1);
        ji2 = findViewById(R.id.Mjisan_2);
        ji3 = findViewById(R.id.Mjisan_3);
        duck1 = findViewById(R.id.Mduck_1);
        duck2 = findViewById(R.id.Mduck_2);
        duck3 = findViewById(R.id.Mduck_3);
        elie1 = findViewById(R.id.Melie_1);
        elie2 = findViewById(R.id.Melie_2);
        elie3 = findViewById(R.id.Melie_3);
        hwi1 = findViewById(R.id.Mhwinix_1);
        hwi2 = findViewById(R.id.Mhwinix_2);
        hwi3 = findViewById(R.id.Mhwinix_3);
        yong1 = findViewById(R.id.Myoungpyung_1);
        yong2 = findViewById(R.id.Myoungpyung_2);
        yong3 = findViewById(R.id.Myoungpyung_3);
        al1 = findViewById(R.id.Malpensia_1);
        al2 = findViewById(R.id.Malpensia_2);
        al3 = findViewById(R.id.Malpensia_3);
        vi1 = findViewById(R.id.Mvivaldi_1);
        vi2 = findViewById(R.id.Mvivaldi_2);
        vi3 = findViewById(R.id.Mvivaldi_3);
        wel1 = findViewById(R.id.Mweli_1);
        wel2 = findViewById(R.id.Mweli_2);
        wel3 = findViewById(R.id.Mweli_3);
        hi1 = findViewById(R.id.Mhione_1);
        hi2 = findViewById(R.id.Mhione_2);
        hi3 = findViewById(R.id.Mhione_3);
        ork1 = findViewById(R.id.Mork_1);
        ork2 = findViewById(R.id.Mork_2);
        ork3 = findViewById(R.id.Mork_3);
        eden1 = findViewById(R.id.Meden_1);
        eden2 = findViewById(R.id.Meden_2);
        eden3 = findViewById(R.id.Meden_3);

    }

    //??????????????? ???????????????
    private void NameClick() {
        //???????????? ?????? ?????? ????????? ????????? ??????
        TextView.OnClickListener onClickListener = new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //??????????????????
                    case R.id.Mstarhill_1:
                        star();
                        break;
                    case R.id.Mstarhill_2:
                        star();
                        break;
                    case R.id.Mstarhill_3:
                        star();
                        break;
                    //??????????????????

                    //???????????????
                    case R.id.Mbears_1:
                        bears();
                        break;
                    case R.id.Mbears_2:
                        bears();
                        break;
                    case R.id.Mbears_3:
                        bears();
                        break;
                    //???????????????

                    //?????????
                    case R.id.Mgonjiam_1:
                        gon();
                        break;
                    case R.id.Mgonjiam_2:
                        gon();
                        break;
                    case R.id.Mgonjiam_3:
                        gon();
                        break;
                    //?????????

                    //?????????????????????
                    case R.id.Mpine_1:
                        pine();
                        break;
                    case R.id.Mpine_2:
                        pine();
                        break;
                    case R.id.Mpine_3:
                        pine();
                        break;
                    //?????????????????????

                    //???????????????
                    case R.id.Mjisan_1:
                        ji();
                        break;
                    case R.id.Mjisan_2:
                        ji();
                        break;
                    case R.id.Mjisan_3:
                        ji();
                        break;
                    //???????????????

                    //??????????????????
                    case R.id.Mduck_1:
                        duck();
                        break;
                    case R.id.Mduck_2:
                        duck();
                        break;
                    case R.id.Mduck_3:
                        duck();
                        break;
                    //??????????????????

                    //????????????
                    case R.id.Mork_1:
                        ork();
                        break;
                    case R.id.Mork_2:
                        ork();
                        break;
                    case R.id.Mork_3:
                        ork();
                        break;
                    //????????????

                    //????????????
                    case R.id.Melie_1:
                        elie();
                        break;
                    case R.id.Melie_2:
                        elie();
                        break;
                    case R.id.Melie_3:
                        elie();
                        break;
                    //????????????

                    //???????????????
                    case R.id.Mvivaldi_1:
                        vi();
                        break;
                    case R.id.Mvivaldi_2:
                        vi();
                        break;
                    case R.id.Mvivaldi_3:
                        vi();
                        break;
                    //???????????????

                    //??????????????????
                    case R.id.Mweli_1:
                        wel();
                        break;
                    case R.id.Mweli_2:
                        wel();
                        break;
                    case R.id.Mweli_3:
                        wel();
                        break;
                    //??????????????????

                    //???????????????
                    case R.id.Mhwinix_1:
                        hwi();
                        break;
                    case R.id.Mhwinix_2:
                        hwi();
                        break;
                    case R.id.Mhwinix_3:
                        hwi();
                        break;
                    //???????????????

                    //???????????????
                    case R.id.Myoungpyung_1:
                        yong();
                        break;
                    case R.id.Myoungpyung_2:
                        yong();
                        break;
                    case R.id.Myoungpyung_3:
                        yong();
                        break;
                    //???????????????

                    //?????????????????????
                    case R.id.Malpensia_1:
                        al();
                        break;
                    case R.id.Malpensia_2:
                        al();
                        break;
                    case R.id.Malpensia_3:
                        al();
                        break;
                    //?????????????????????

                    //??????????????????
                    case R.id.Mhione_1:
                        hi();
                        break;
                    case R.id.Mhione_2:
                        hi();
                        break;
                    case R.id.Mhione_3:
                        hi();
                        break;
                    //??????????????????

                    //?????????????????????
                    case R.id.Meden_1:
                        eden();
                        break;
                    case R.id.Meden_2:
                        eden();
                        break;
                    case R.id.Meden_3:
                        eden();
                        break;
                    //?????????????????????

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

    private void star() {
        Name = star2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void bears() {
        Name = bears2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void gon() {
        Name = gon2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void pine() {
        Name = pine2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void ji() {
        Name = ji2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void duck() {
        Name = duck2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void ork() {
        Name = ork2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void vi() {
        Name = vi2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void wel() {
        Name = wel2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void elie() {
        Name = elie2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void hwi() {
        Name = hwi2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void yong() {
        Name = yong2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void hi() {
        Name = hi2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void al() {
        Name = al2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }

    private void eden() {
        Name = eden2.getText().toString();
        Intent intent = new Intent(getApplicationContext(), b_Infomation.class);
        intent.putExtra("SnowPark", Name);
        startActivity(intent);
    }
}