package com.example.national_ski_resorts;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link b_frag_map#newInstance} factory method to
 * create an instance of this fragment.
 */
public class b_frag_map extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SupportMapFragment supportMapFragment;
    private GoogleMap mMap;
    double jisanL =37.217314, jisanH = 127.344085,//지산리조트
            bearstownL =37.796476, bearstownH = 127.247714,//베어스타운
            starhillL =37.662935, starhillH = 127.269144,//스타힐리조트
            gonjiamL =37.337965, gonjiamH = 127.295152,//곤지암리조트
            pineL =37.213229, pineH = 127.295120,//양지파인리조트
            duckyousanL =35.891784, duckyousanH = 127.733567,//덕유산리조트
            edenveleyL =35.429238, edenveleyH = 128.984500,//에덴벨리리조트
            elisianL =37.822262, elisianH = 127.589811,//엘리시안리조트
            orkvelyL =37.408687, orkvelyH = 127.807632247714,//오크밸리
            welihilyL =37.491293, welihilyH = 128.250558,//웰리힐리파크
            hioneL =37.206808, hioneH = 128.837993,//하이원리조트
            vivaldiL =37.643775, vivaldiH = 127.683737,//비발디파크
            hweinixL =37.579269, hweinixH = 128.331566,//휘닉스파크
            alpensiaL =37.654420, alpensiaH = 128.671732,//알펜시아리조트
            yongpyungL =37.644480, yongpyungH = 128.679917;//용평리조트

    public b_frag_map() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment b_map.
     */
    // TODO: Rename and change types and number of parameters
    public static b_frag_map newInstance(String param1, String param2) {
        b_frag_map fragment = new b_frag_map();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_map,null);
        b_Infomation activity = (b_Infomation) getActivity();
        String Keyword = ((b_Infomation) activity).STARHILL;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fragmentManager = getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        if(supportMapFragment == null){
            supportMapFragment = SupportMapFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.map, supportMapFragment).commit();
        }else{
            supportMapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap =googleMap;
        onAddMarker();
        LatLngBounds adelaideBounds = new LatLngBounds(
                new LatLng(34.503003, 126.260760), // 남서SW bounds
                new LatLng(38.382058, 129.780065)  // 북동NE bounds
        );
        mMap.setMinZoomPreference(7.5f);
        mMap.setLatLngBoundsForCameraTarget(adelaideBounds);
/*
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);


        // 기존에 사용하던 다음 2줄은 문제가 있습니다.

        // CameraUpdateFactory.zoomTo가 오동작하네요.
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));*/

        SetLocation();
    }

    @Override
    public void onResume() {
        super.onResume();
        supportMapFragment.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        supportMapFragment.onPause();
    }
    public void onAddMarker(){

        LatLng BEARS = new LatLng(bearstownL, bearstownH);
        MarkerOptions markerBEARS = new MarkerOptions();
        markerBEARS.position(BEARS);
        markerBEARS.title("베어스타운");
        markerBEARS.snippet("경기도 포천시 내촌면 소학리 305");

        LatLng STAR = new LatLng(starhillL, starhillH);
        MarkerOptions markerSTAR = new MarkerOptions();
        markerSTAR.position(STAR);
        markerSTAR.title("스타힐리조트");
        markerSTAR.snippet("경기도 남양주시 화도읍 먹갓로 96");

        LatLng GON = new LatLng(gonjiamL, gonjiamH);
        MarkerOptions markerGON = new MarkerOptions();
        markerGON.position(GON);
        markerGON.title("곤지암리조트");
        markerGON.snippet("경기도 광주시 도척면 도척윗로 278");

        LatLng PINE = new LatLng(pineL, pineH);
        MarkerOptions markerPINE = new MarkerOptions();
        markerPINE.position(PINE);
        markerPINE.title("양지파인리조트");
        markerPINE.snippet("경기도 용인시 처인구 양지면 남곡리 34-1");

        LatLng JI = new LatLng(jisanL, jisanH);
        MarkerOptions markerJI = new MarkerOptions();
        markerJI.position(JI);
        markerJI.title("지산리조트");
        markerJI.snippet("경기도 이천시 마장면 해월리 357-3");

        LatLng DUCK = new LatLng(duckyousanL, duckyousanH);
        MarkerOptions markerDUCK = new MarkerOptions();
        markerDUCK.position(DUCK);
        markerDUCK.title("덕유산리조트");
        markerDUCK.snippet("전라북도 무주군 설천면 심곡리 산43-15");

        LatLng ORK = new LatLng(orkvelyL, orkvelyH);
        MarkerOptions markerORK = new MarkerOptions();
        markerORK.position(ORK);
        markerORK.title("한솔오크밸리");
        markerORK.snippet("강원도 원주시 지정면 판대리 산1-12");

        LatLng VI = new LatLng(vivaldiL, vivaldiH);
        MarkerOptions markerVI = new MarkerOptions();
        markerVI.position(VI);
        markerVI.title("비발디파크");
        markerVI.snippet("강원도 홍천군 서면 한치골길 262");

        LatLng WEL = new LatLng(welihilyL, welihilyH);
        MarkerOptions markerWEL = new MarkerOptions();
        markerWEL.position(WEL);
        markerWEL.title("웰리힐리파크");
        markerWEL.snippet("강원도 횡성군 둔내면 두원리 204");

        LatLng ELI = new LatLng(elisianL, elisianH);
        MarkerOptions markerELI = new MarkerOptions();
        markerELI.position(ELI);
        markerELI.title("엘리시안리조트");
        markerELI.snippet("강원도 춘천시 남산면 북한강변길 688");

        LatLng HWE = new LatLng(hweinixL, hweinixH);
        MarkerOptions markerHWE = new MarkerOptions();
        markerHWE.position(HWE);
        markerHWE.title("휘닉스파크");
        markerHWE.snippet("강원도 평창군 봉평면 면온리 946-14");

        LatLng YONG = new LatLng(yongpyungL, yongpyungH);
        MarkerOptions markeYONG = new MarkerOptions();
        markeYONG.position(YONG);
        markeYONG.title("용평리조트");
        markeYONG.snippet("강원도 평창군 대관령면 용산리 99-5");

        LatLng HI = new LatLng(hioneL, hioneH);
        MarkerOptions markerHI = new MarkerOptions();
        markerHI.position(HI);
        markerHI.title("하이원리조트");
        markerHI.snippet("강원도 정선군 고한읍 고한리");

        LatLng AL = new LatLng(alpensiaL, alpensiaH);
        MarkerOptions markerAL = new MarkerOptions();
        markerAL.position(AL);
        markerAL.title("알펜시아리조트");
        markerAL.snippet("강원도 횡성군 둔내면 두원리 204");

        LatLng EDEN = new LatLng(edenveleyL, edenveleyH);
        MarkerOptions markerEDEN = new MarkerOptions();
        markerEDEN.position(EDEN);
        markerEDEN.title("에덴벨리리조트");
        markerEDEN.snippet("경상남도 양산시 원동면 어실로 1206");

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
    private void SetLocation(){
        b_Infomation activity = (b_Infomation) getActivity();
        String Keyword = ((b_Infomation) activity).STARHILL;
        if(Keyword.equals("스타힐리조트")){
            LatLng STAR = new LatLng(starhillL, starhillH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STAR, 15));
        }else if(Keyword.equals("베어스타운")){
            LatLng BEARS = new LatLng(bearstownL, bearstownH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BEARS, 15));
        }else if(Keyword.equals("곤지암리조트")){
            LatLng GON = new LatLng(gonjiamL, gonjiamH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GON, 15));
        }else if(Keyword.equals("양지파인리조트")){
            LatLng PINE = new LatLng(pineL, pineH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PINE, 15));
        }else if(Keyword.equals("지산리조트")){
            LatLng JI = new LatLng(jisanL, jisanH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(JI, 15));
        }else if(Keyword.equals("덕유산리조트")){
            LatLng DUCK = new LatLng(duckyousanL, duckyousanH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DUCK, 15));
        }else if(Keyword.equals("한솔오크밸리")){
            LatLng ORK = new LatLng(orkvelyL, orkvelyH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ORK, 15));
        }else if(Keyword.equals("비발디파크")){
            LatLng VI = new LatLng(vivaldiL, vivaldiH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(VI, 15));
        }else if(Keyword.equals("웰리힐리파크")){
            LatLng WEL = new LatLng(welihilyL, welihilyH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(WEL, 15));
        }else if(Keyword.equals("엘리시안리조트")){
            LatLng ELI = new LatLng(elisianL, elisianH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ELI, 15));
        }else if(Keyword.equals("휘닉스파크")){
            LatLng HWE = new LatLng(hweinixL, hweinixH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HWE, 15));
        }else if(Keyword.equals("용평리조트")){
            LatLng YONG = new LatLng(yongpyungL, yongpyungH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(YONG, 15));
        }else if(Keyword.equals("하이원리조트")){
            LatLng HI = new LatLng(hioneL, hioneH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HI, 15));
        }else if(Keyword.equals("알펜시아리조트")){
            LatLng AL = new LatLng(alpensiaL, alpensiaH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AL, 15));
        }else if(Keyword.equals("에덴벨리리조트")){
            LatLng EDEN = new LatLng(edenveleyL, edenveleyH);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EDEN, 15));
        }
    }
}