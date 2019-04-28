package com.tje.playstorecopycat;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tje.playstorecopycat.adapters.AppAdapter;
import com.tje.playstorecopycat.databinding.ActivityMainBinding;
import com.tje.playstorecopycat.datas.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static   int REQ_FOR_FILTER = 150;

    AppAdapter mAppAdapter;

    List<App> appList = new ArrayList<>();

    ActivityMainBinding act;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = DataBindingUtil.setContentView(this,R.layout.activity_main);
        fillApps();

        mAppAdapter = new AppAdapter(MainActivity.this,appList);
        act.appRankListView.setAdapter(mAppAdapter);
        // Q1  확인버튼이 눌리면 확인 버튼을 눌렀습니다. 라는 토스트를 띄워봅시다.
        act.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "확인 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();

                //확인 버튼만 있도록 AlertDialog
                AlertDialog.Builder okAlert= new AlertDialog.Builder(MainActivity.this);

                okAlert.setTitle("게임 추가 알림");
                okAlert.setMessage("임시 게임이 추가 됩니다.");
                okAlert.setPositiveButton("확인",null);
                okAlert.show();




                appList.add(new App(1,"임시게임","미상",5,3000,true));
                mAppAdapter.notifyDataSetChanged();



                //Ex , f리스트 앱이 6개 : 마지막꺼는 몇번 ?5


                act.appRankListView.smoothScrollToPosition(appList.size()-1);
            }
        });

        act.appRankListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                App clickedAppData  = appList.get(position);

                //Toast.makeText(MainActivity.this,String.format("%d번 줄이 클릭",position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,AppDetailActivity.class);
            /*    intent.putExtra("제목",clickedAppData.title);
                intent.putExtra("회사이름",clickedAppData.companyName);*/
                intent.putExtra("앱정보",clickedAppData);

                startActivity(intent);
            }
        });

        act.appRankListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(MainActivity.this, String.format("%d번 줄 클릭",position), Toast.LENGTH_SHORT).show()

                final int  finalposition = position;
                AlertDialog.Builder alert  = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("앱 삭제 확인");
                alert.setMessage("정말 이앱을 삭제하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        appList.remove(finalposition);

                        mAppAdapter.notifyDataSetChanged();

                        Toast.makeText(MainActivity.this, "해당 앱이 삭제 되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                });
                alert.setNegativeButton("취소",null);
                alert.show();


                return true;
            }
        });

        act.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FilterActivity.class);
                startActivityForResult(intent,REQ_FOR_FILTER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("액티비티 결과","결과가 돌아옴!");
        Log.d("리퀘스트 코드 ", requestCode+"");
        Log.d("리절트 코드",resultCode+"");
        
        if(requestCode == REQ_FOR_FILTER) {
            //필터를 설정하러 갔다 돌아온게 맞다! 
            if( resultCode == RESULT_OK){
                //확인버튼이 눌린게 맞다.
               // Toast.makeText(this, "필터가 설정되었습니다.", Toast.LENGTH_SHORT).show();
                int filteredRating = data.getIntExtra("최소평점",0);
                act.filterRatingTxt.setText(String.format("(현재 필터:%d점)",filteredRating));
            }
            else{
                 //확인 아니고, 취소되었다. 
                Toast.makeText(this, "필터 설정을 취소 했습니다.", Toast.LENGTH_SHORT).show();
                
            }
        }
    }

    void fillApps() {

        appList.add(new App(1,"아스팔트 8: 에어본","GameLoft",5,6000,true));
        appList.add(new App(2,"MineCraft - Pocket Edition","Mojang",4,1000,true));
        appList.add(new App(3,"아스팔트 7 : 하트","GameLoft",2,3000,false));
        appList.add(new App(4,"팔라독(Paladog)","FazeCat",3,2000,false));
        appList.add(new App(5,"Plants Vs . Zombies","EA Swiss Sarl",1,4000,false));
        appList.add(new App(6,"스왐피(Swampy)","Dizney",4,7000,false));
        appList.add(new App(1,"아스팔트 8: 에어본","GameLoft",5,6000,true));
        appList.add(new App(2,"MineCraft - Pocket Edition","Mojang",4,1000,true));
        appList.add(new App(3,"아스팔트 7 : 하트","GameLoft",2,3000,false));
        appList.add(new App(4,"팔라독(Paladog)","FazeCat",3,2000,false));
        appList.add(new App(5,"Plants Vs . Zombies","EA Swiss Sarl",1,4000,false));
        appList.add(new App(6,"스왐피(Swampy)","Dizney",4,7000,false));
        appList.add(new App(1,"아스팔트 8: 에어본","GameLoft",5,6000,true));
        appList.add(new App(2,"MineCraft - Pocket Edition","Mojang",4,1000,true));
        appList.add(new App(3,"아스팔트 7 : 하트","GameLoft",2,3000,false));
        appList.add(new App(4,"팔라독(Paladog)","FazeCat",3,2000,false));
        appList.add(new App(5,"Plants Vs . Zombies","EA Swiss Sarl",1,4000,false));
        appList.add(new App(6,"스왐피(Swampy)","Dizney",4,7000,false));
    }
}
