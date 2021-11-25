package com.example.national_ski_resorts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link b_frag_review#newInstance} factory method to
 * create an instance of this fragment.
 */
public class b_frag_review extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RatingBar ratingBar, ratingBar_review;
    private ImageView profile,sortIv;
    private RecyclerView recyclerView;
    private TextView ratingPoint, ratingCount, sortTv;
    private LinearLayout sortLL,login_true,login_false;
    private Button login_go;
    private Handler mHandler = null;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private FirebaseAuth mAuth;
    int Login;

    public b_frag_review() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment b_review.
     */
    // TODO: Rename and change types and number of parameters
    public static b_frag_review newInstance(String param1, String param2) {
        b_frag_review fragment = new b_frag_review();
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
        //View 객체선언 하여 레이아웃을 인플레이트 해주고 return view로 사용
        View view = inflater.inflate(R.layout.fragment_b_review,null);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        pref = this.getActivity().getSharedPreferences("LogIn", Activity.MODE_PRIVATE);
        editor = pref.edit();
        Login = pref.getInt("LogInOK", 0);

        ratingBar= view.findViewById(R.id.rating);
        ratingPoint= view.findViewById(R.id.rating_point);
        ratingCount= view.findViewById(R.id.rating_count);

        profile= view.findViewById(R.id.profile);
        ratingBar_review = view.findViewById(R.id.rating_review);

        sortIv= view.findViewById(R.id.sort_iv);
        sortLL= view.findViewById(R.id.sort_layout);
        sortTv= view.findViewById(R.id.sort_tv);

        login_true= view.findViewById(R.id.login_true);
        login_false= view.findViewById(R.id.login_false);
        login_go= view.findViewById(R.id.bt_go_login);

        //login_false.setVisibility(View.GONE);

        ratingReview();
        Log.e("Login", String.valueOf(Login));
        mHandler = new Handler();
        //로그인 유무를 판단 합니다. (현재 구글 로그인)
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // UI 작업 수행 X
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // UI 작업 수행 로그인 쉐어드 확인할것
                        if (Login == 0) {
                            //로그인 화면 있음(로그인 되지 않음)
                            Log.e("Login 안되어있음", String.valueOf(Login));
                            login_false.setVisibility(View.VISIBLE);
                            login_true.setVisibility(View.GONE);
                        } else {
                            Log.e("mAuth not null", "not null");
                            Log.e("Login", String.valueOf(Login));
                            Picasso.get().load(user.getPhotoUrl()).centerInside().fit().into(profile);
                            login_false.setVisibility(View.GONE);
                            login_true.setVisibility(View.VISIBLE);
                            //Picasso.get().load(user.getPhotoUrl()).centerInside().fit().into(ProfileImg);
                            //ProfileName.setText(user.getDisplayName());
                            //ProfileEmail.setText(user.getEmail());
                        }
                    }
                });
            }
        });
        t.start();

        login_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), z_Login.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void ratingReview(){
        ratingBar_review.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                b_Infomation activity = (b_Infomation) getActivity();
                String Keyword = ((b_Infomation) activity).STARHILL;
                Intent intent = new Intent(getContext(), b_RatingReview.class);
                intent.putExtra("Rating",rating);
                intent.putExtra("Keyword",Keyword);
                startActivity(intent);
            }
        });
    }
}