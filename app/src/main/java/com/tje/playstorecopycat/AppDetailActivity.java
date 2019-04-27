package com.tje.playstorecopycat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.tje.playstorecopycat.databinding.ActivityAppDetailBinding;
import com.tje.playstorecopycat.databinding.ActivityMainBinding;
import com.tje.playstorecopycat.datas.App;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppDetailActivity extends AppCompatActivity {

  ActivityAppDetailBinding act;
  App mAppData; //  이 화면에서 다룰 앱의 정보를 가진 멤버 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = DataBindingUtil.setContentView(this,R.layout.activity_app_detail);

     /*   String appTitle = getIntent().getStringExtra("제목");
        String appCompanyName = getIntent().getStringExtra("회사이름");*/

        mAppData = (App)getIntent().getSerializableExtra("앱정보");

        act.appTitleTxt.setText(mAppData.title);
        act.companyNameTxt.setText(mAppData.companyName);

        act.userRatingTxt.setText(String.format("%d점",mAppData.userRating));

        // 구매 여부에 따라 필요한 버트만 보여주기

        if(mAppData.isMine){
            act.removeBtn.setVisibility(View.VISIBLE);
            act.launchBtn.setVisibility(View.VISIBLE);;
            act.purchaseBtn.setVisibility(View.GONE);
        }else{
            act.removeBtn.setVisibility(View.GONE);
            act.launchBtn.setVisibility(View.GONE);;
            act.purchaseBtn.setVisibility(View.VISIBLE);
            //구매하기 버튼의 문구도 올바른 가격으로
            act.purchaseBtn.setText(String.format("구매하기(%,d원)",mAppData.price));
        }

        act.dialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri phoneUri = Uri.parse("tel:019-9876-5432");
                Intent intent = new Intent(Intent.ACTION_DIAL, phoneUri);
                startActivity(intent);

            }
        });

        act.dataTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("텍스트뷰","실제로 동작하나");
                DatePickerDialog dpd = new DatePickerDialog(AppDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Toast.makeText(AppDetailActivity.this,  String.format("%d년 %d 월 %d 일",year,month,dayOfMonth), Toast.LENGTH_SHORT).show();
                        Calendar cal = Calendar.getInstance(); // new Calendar 라고 만들지 않는다. 싱글턴 패턴의 일종,
                        //  항목별로 어떤값을 갖게 할건지? 코딩 방식
                        cal.set(Calendar.YEAR,year);
                        cal.set(Calendar.MONTH,month);
                        cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        // 2  년/월/일 한꺼번에 세팅 .
                        cal.set(year,month,dayOfMonth);
                        // 같은 메소드인데 ,  arg 의 종류/갯수에 따라 다른 행동을함.  = > overLoading 의 예시

                        // cal 에 저장된 값을 string으로(양식에 맞게) 바꿔서Textview 로 세팅.
                        // 날짜를 양식으로 바꾸고 싶을때  : simpleDateformat

                        // 어떤 양식으로 문자를 출력할지 지정. 양식을 지정

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일");
                        // 지정된 양식으로 Calendar 변수를 String으로 변환 .

                        String dateStr  = sdf.format(cal.getTimeInMillis());
                        // 만들어진 String을 화면에 출력
                        act.dataTxt.setText(dateStr);



                    }
                },2019,3,27);
                dpd.show();
            }
        });

    }
}
