package com.tje.playstorecopycat;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tje.playstorecopycat.adapters.AppAdapter;
import com.tje.playstorecopycat.databinding.ActivityMainBinding;
import com.tje.playstorecopycat.datas.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
                Toast.makeText(MainActivity.this, "확인 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        act.appRankListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this,String.format("%d번 줄이 클릭",position), Toast.LENGTH_SHORT).show();

            }
        });

        act.appRankListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, String.format("%d번 줄 클릭",position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    void fillApps() {

        appList.add(new App(1,"아스팔트 8: 에어본","GameLoft",5,6000,true));
        appList.add(new App(2,"MineCraft - Pocket Edition","Mojang",4,1000,true));
        appList.add(new App(1,"아스팔트 7 : 하트","GameLoft",2,3000,false));
        appList.add(new App(1,"팔라독(Paladog)","FazeCat",3,2000,false));
        appList.add(new App(1,"Plants Vs . Zombies","EA Swiss Sarl",1,4000,false));
        appList.add(new App(1,"스왐피(Swampy)","Dizney",4,7000,false));
    }
}
