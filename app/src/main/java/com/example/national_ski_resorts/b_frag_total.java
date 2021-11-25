package com.example.national_ski_resorts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link b_frag_total#newInstance} factory method to
 * create an instance of this fragment.
 */

public class b_frag_total extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String IP_ADDRESS = "www.pokeshooting.cf/";
    private static String TAG = "phpexample";

    // TODO: Rename and change types of parameters
    private String mJsonString;
    private String mParam1;
    private String mParam2;
    private TextView frag_call,frag_call2, frag_location, mTextViewResult, mTextViewResult2;
    private ArrayList<SkiData> mArrayList; //등록된 스키장리스트
    private SkiAdapter mAdapter; //스키장 정보 어뎁터
    private RecyclerView mRecyclerView; //리사이클러뷰


    public b_frag_total() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment b_total.
     */
    // TODO: Rename and change types and number of parameters
    public static b_frag_total newInstance(String param1, String param2) {
        b_frag_total fragment = new b_frag_total();
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
        View view = inflater.inflate(R.layout.fragment_b_total,null);

        mRecyclerView = view.findViewById(R.id.listView_main_list);
        frag_call = view.findViewById(R.id.frag_call);
        frag_call2 = view.findViewById(R.id.frag_call2);
        frag_location = view.findViewById(R.id.frag_location);
        mTextViewResult = view.findViewById(R.id.mTextViewResult);
        mTextViewResult2 = view.findViewById(R.id.mTextViewResult2);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(b_frag_total.this.getContext()));

        mArrayList = new ArrayList<>();
        mAdapter = new SkiAdapter(b_frag_total.this.getActivity(), mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();
        b_Infomation activity = (b_Infomation) getActivity();


        String Keyword = ((b_Infomation) activity).STARHILL;
        b_frag_total.GetData task = new b_frag_total.GetData();
        task.execute( "http://" + IP_ADDRESS + "dbAndroidQuerySkiInfo.php", Keyword);

        frag_call = view.findViewById(R.id.frag_call);
        mTextViewResult = view.findViewById(R.id.mTextViewResult);
        frag_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String call = frag_call.getText().toString();
                String[] array = call.split("-");
                StringBuilder sb = new StringBuilder();
                for(int i=0;i<array.length;i++) {
                    sb.append(array[i]);
                    Log.e("sb",sb.toString());
                }
                String callnum = sb.toString();
                Log.e("callnum",callnum.toString());
                Intent tt = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+callnum));
                startActivity(tt);
            }
        });

        return view;
    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override //데이터를 받아오는 동안 뺑글이 메시지
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(b_frag_total.this.getContext(),
                    "잠시만 기다려주세요.", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);
            if (result == null){
                //결과값이 없을때 텍스트 기재
                mTextViewResult.setText(errorString);
            }
            else {
                b_Infomation activity = (b_Infomation) getActivity();
                mTextViewResult2.setText(((b_Infomation) activity).STARHILL.toString());
                mJsonString = result;
                showResult();

                //frag_call.setText(result);
            }
        }


        @Override //백그라운드에서 하는 역할
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = "ski=" + params[1];

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);
                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    //서버와 연결이 되었을때
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    //서버와 연결이 안 되었을때
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString().trim();

            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();
                return null;
            }
        }
        private void showResult(){

            String TAG_JSON="webnautes";

            String TAG_ATTIME = "attime";
            String TAG_STARTTIME = "starttime";
            String TAG_ENDTIME ="endtime";
            String TAG_TEXCOST ="texcost";
            String TAG_LOCA = "loca";
            String TAG_CALLNUM = "callnum";

            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


                for(int i=0;i<jsonArray.length();i++){

                    JSONObject item = jsonArray.getJSONObject(i);

                    String attime = item.getString(TAG_ATTIME);
                    String starttime = item.getString(TAG_STARTTIME);
                    String endtime = item.getString(TAG_ENDTIME);
                    String texcost = item.getString(TAG_TEXCOST);
                    String loca = item.getString(TAG_LOCA);
                    frag_location.setText(loca);
                    Log.e("---------loca----------",loca);
                    String callnum = item.getString(TAG_CALLNUM);
                    frag_call.setText(String.valueOf(callnum));
                    Log.e("---------callnum----------",callnum.toString());

                    SkiData skiData = new SkiData();
                    skiData.setSki_attime(attime);
                    skiData.setSki_starttime(starttime);
                    skiData.setSki_endtime(endtime);
                    skiData.setSki_texcost(texcost);

                    mArrayList.add(skiData);
                    mAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                Log.d(TAG, "showResult : ", e);
            }
        }

        //이부분 추가
        void loadDB(){
            //volley library로 사용 가능
            //이 예제에서는 전통적 기법으로 함.
            new Thread(){
                @Override
                public void run() {

                    String serverUri="http://umul.dothome.co.kr/Android/loadDB.php";

                    try {
                        URL url= new URL(serverUri);

                        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setDoInput(true);
                        //connection.setDoOutput(true);// 이 예제는 필요 없다.
                        connection.setUseCaches(false);

                        InputStream is=connection.getInputStream();
                        InputStreamReader isr= new InputStreamReader(is);
                        BufferedReader reader= new BufferedReader(isr);

                        final StringBuffer buffer= new StringBuffer();
                        String line= reader.readLine();
                        while (line!=null){
                            buffer.append(line+"\n");
                            line= reader.readLine();
                        }
                        
                        //읽어오는 작업이 성공 했는지 확인
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(b_frag_total.this.getContext()).setMessage(buffer.toString()).create().show();
                            }
                        });

                    } catch (MalformedURLException e) { e.printStackTrace(); } catch (IOException e) {e.printStackTrace();}
                }
            }.start();
        }//loadDB() ..



    }
}