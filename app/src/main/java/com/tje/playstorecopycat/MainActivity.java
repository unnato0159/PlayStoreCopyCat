package com.tje.playstorecopycat;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tje.playstorecopycat.databinding.ActivityMainBinding;
import com.tje.playstorecopycat.datas.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<App> appList = new ArrayList<>();

    ActivityMainBinding act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = DataBindingUtil.setContentView(this,R.layout.activity_main);
        fillApps();
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
