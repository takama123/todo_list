package com.example.pc.todo_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.pc.todo_list.fragment.AddMissionFragment;


/**
 * Created by HCD-Fresher057 on 1/11/2017.
 */

public class MissionActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //display list mission
        AddMissionFragment fr = new AddMissionFragment();
        ft.replace(R.id.frameDetailMission, fr, "fr1");
        ft.addToBackStack("fr1");
        Log.d("TEST", "setListMission");
        ft.commit();

        ImageButton btnBack = (ImageButton) findViewById(R.id.imgBack);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.imgBack) finish();
    }
}
