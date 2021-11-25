package com.example.national_ski_resorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class z_Login extends AppCompatActivity {

    /*private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "Oauth2Google";

    GoogleSignInClient mGoogleSignInClient;*/
    private static final String TAG = "GoogleLoginActivity";
    private FirebaseAuth mAuth = null;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;
    private ImageView img;
    private TextView t1,t2;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_login);
        //구글 버튼 커스텀
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.getChildAt(0);
        TextView textView = (TextView) signInButton.getChildAt(0);
        textView.setText("Google 계정으로 로그인");

        pref = getSharedPreferences("LogIn", Activity.MODE_PRIVATE);
        editor = pref.edit();


        img = findViewById(R.id.testimg);
        t1 = findViewById(R.id.testmail);
        t2 = findViewById(R.id.testnsame);
        //FirebaseAuth.getInstance는 FirebaseAuth를 사용하기 위해 인스턴스 받아오기 위함
        mAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        /*
        //로그인 하고 다음에 어플리케이션을 켰을때 바로 다음 화면으로 넘어가게 하고 싶다면 이 코드를 안에 넣어주자!
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplication(), AfterActivity.class);
            startActivity(intent);
            finish();
        }*/

/*
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        Log.e("1","1");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        Log.e("1","2");
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Log.e("1","3");
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        Log.e("1","4");
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        Log.e("1","5");
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Log.e("1","6");
        updateUI(account);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
    //TODO 여기 업데이트 부분 예제대로 다른 액티비로 넘겼을때 잘 되는지 확인
    private void updateUI(GoogleSignInAccount account) {
        Intent intent = new Intent(getApplicationContext(), a_Main.class);
        intent.putExtra(a_Main.GOOGLE_ACCOUNT, account);
        intent.putExtra("loginyes",true);

        startActivityForResult(intent,1001);
        finish();
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/
    //로그인버튼(구글)(회원이 아닐경우 회원가입이 될 수 있도록 한다)
    signInButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            signIn();//버튼클릭 함수 실행 시 updateUI에서 화면 이동제어
        }
    });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //TODO 이곳에서 아이디 중복확인절차 거친 후 새로운 아이디일 경우 내 DB에 넣어주어야한다.
                // if(!DB 조회값.equals(account.getEmail())){
                // Intent intent = new Intent(this, 구글아이디 DB에 넣을.class)
                // startActivity(intent);
                //TODO  }

                //t1.setText(account.getEmail());
                //Picasso.get().load(account.getPhotoUrl()).centerInside().fit().into(img);
                //t2.setText(account.getDisplayName());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Snackbar.make(findViewById(R.id.layout_main), "Authentication Successed.", Snackbar.LENGTH_SHORT).show();
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;

                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.layout_main), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) { //update ui code here
        //유저의값이 null이 아닐경우 메인실행
        if (user != null) {
            t1.setText(user.getEmail());
            Picasso.get().load(user.getPhotoUrl()).centerInside().fit().into(img);
            t2.setText(user.getDisplayName());
            Log.e(TAG, Objects.requireNonNull(user.getEmail()));
            Log.e(TAG,user.getDisplayName());
            Log.e(TAG,user.getProviderId());

            editor.putInt("LogInOK", 1);
            editor.apply();
            int Login = pref.getInt("LogInOK", 0);
            Log.e("Login 상태는?", String.valueOf(Login));

            Intent intent = new Intent(this, a_Main.class);
            intent.putExtra("true",true);
            startActivity(intent);
            finish();
        }
    }
}