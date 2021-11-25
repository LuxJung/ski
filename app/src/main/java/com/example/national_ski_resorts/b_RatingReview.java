package com.example.national_ski_resorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class b_RatingReview extends AppCompatActivity {

    private Toolbar toolbar;
    private RatingBar ratingBar;
    private TextView title,tv_ratingPoint, tv_id;
    private EditText editText;
    private ImageView profile;
    private LinearLayout addPicture, addCamera;
    private Intent intent;
    private Button upRode;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ReviewImgData> mArrayList; //등록된 유저리스트
    private ReviewImgAdapter mAdapter; //유저 정보 어뎁터
    private RecyclerView mRecyclerView; //리사이클러뷰
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;
    private File tempFile;
    private Boolean isCamera = false;
    private String imageFilePath;
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    //툴바 뒤로가기
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_rating_review);
        title = findViewById(R.id.review_name);
        toolbar = (Toolbar)findViewById(R.id.iRatingReview_toolbar);
        ratingBar = (RatingBar)findViewById(R.id.rating_review);
        tv_id = findViewById(R.id.tv_id);
        tv_ratingPoint = findViewById(R.id.point);
        editText = findViewById(R.id.et_text);
        profile = findViewById(R.id.profile);
        addPicture = findViewById(R.id.add_picture);//갤러리
        addCamera = findViewById(R.id.add_photo);//카메라
        mRecyclerView = findViewById(R.id.img_recyclerview);
        upRode = findViewById(R.id.up_rode);//업로드버튼 업로드 누를 시 서버에 데이터 저장
        //리사이클러뷰 셋팅
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mArrayList = new ArrayList<>();
        mAdapter = new ReviewImgAdapter(b_RatingReview.this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);
        mArrayList.clear();
        mAdapter.notifyDataSetChanged();

        //파이어베이스 셋팅
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (mAuth.getCurrentUser() == null){

        }else{
            //파이어베이스 프로필 가져오기
            Picasso.get().load(user.getPhotoUrl()).centerInside().fit().into(profile);
            tv_id.setText(user.getDisplayName());
        }


        //툴바셋팅
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //앞에서 설정한 별점 가지고오기
        intent=getIntent();
        float ittnt = intent.getFloatExtra("Rating",0f);
        ratingBar.setRating(ittnt);
        tv_ratingPoint.setText(ittnt+" 점");

        //툴바 타이틀 가져오기
        String Keyword = intent.getStringExtra("Keyword");
        title.setText(Keyword);

        //안에서 별점 재설정 하기
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tv_ratingPoint.setText(rating+" 점");
            }
        });
/*
        //사진추가시 선택할수 있도록 팝업액티비티 띄움움 -> 분리하였음 각 버튼별 기능 작동 하도록 변경
       addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), popup_picture.class);
                startActivity(intent);
            }
        });
*/
        //갤러리접근
        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlbum();
            }
        });
        //카메라촬영
        addCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        tedPermission();



        //업로드하기
        upRode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 여기서 php데이터 전송이 이루어 져야합니다.
                finish();
            }
        });
    }
    //사진권한퍼미션알림띄우기
    private void tedPermission() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                //Toast.makeText(getApplicationContext(), "권한이 허용됨.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // Toast.makeText(getApplicationContext(), "권한이 거부됨.", Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }


    //uri 주소 가지고 오기
    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    //갤러리접근
    private void goToAlbum() {
        isCamera = false;
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }
    //카메라접근
    private void takePhoto() {
        isCamera = true;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            tempFile = null;
            try {
                tempFile = createImageFile();
                //tempFile 의 Uri 경로를 intent 에 추가해 줘야 합니다.
                //이는 카메라에서 찍은 사진이 저장될 주소를 의미합니다.
                // 예제에서는 tempFile 을 전역변수로 해서 사용하기 때문에 이
                // tempFile 에 카메라에서 촬영한 이미지를 넣어줄꺼에요!
            } catch (IOException e) {
                Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                finish();
                e.printStackTrace();
            }
            if (tempFile != null) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Uri photoUri = FileProvider.getUriForFile(this,
                            getPackageName(), tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                } else {
                    Uri photoUri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                }
            }
        }
    }

    //파일생성
    private File createImageFile() throws IOException {
        // 이미지 파일 이름 ( blackJin_{시간}_ )
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }



    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != b_RatingReview.RESULT_OK) {//예외처리
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            if(tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e("TAG", tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return;
        }
        //갤러리크기조절 다시
        if (requestCode == PICK_FROM_ALBUM) {//갤러리
            // Uri photoUri = data.getData();
            // Cursor cursor = null;
            try {
                // 선택한 이미지에서 비트맵 생성
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                mArrayList.add(new ReviewImgData(img));
                in.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            mAdapter.notifyDataSetChanged();
        }else if (requestCode == PICK_FROM_CAMERA) {//카메라
            setImage();
        }
    }
    //이미지세팅
    private void setImage() {
        ImageResizeUtils.resizeFile(tempFile, tempFile, 1280, isCamera);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        mArrayList.add(new ReviewImgData(originalBm));

        /*image.setImageBitmap(originalBm);
        Log.d("카메라", originalBm.toString());
        Log.d("카메라", getImageUri(image.getContext(), originalBm).toString());*/
    }
}