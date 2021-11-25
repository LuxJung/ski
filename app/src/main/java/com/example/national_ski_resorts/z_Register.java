package com.example.national_ski_resorts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class z_Register extends AppCompatActivity {
    private static String IP_ADDRESS = "www.pokeshooting.cf/";
    
    private EditText email, pw, pwck;
    private Button joinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_register);

        email = findViewById(R.id.joinEmail);
        pw = findViewById(R.id.joinPw);
        pwck = findViewById(R.id.joinPwck);
        joinbtn = findViewById(R.id.joinbtn);

        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String JoinEmail = email.getText().toString();
                String JoinPw = pw.getText().toString();
                String JoinPwchk = pwck.getText().toString();

                if(!JoinPw.equals(JoinPwchk)){

                    Toast.makeText(getApplicationContext(), "비밀번호일치x",Toast.LENGTH_SHORT).show();;
                                        


                }else{
                    Intent intent = new Intent(getApplicationContext(), a_FilterResult.class);



                }



                //쿼리문으로 넣기전 중복확인 할 것
                //중복일 경우 중복 메시지를 띄울 것
                //구글일 경



           }
        });

    }//OnCreat
}