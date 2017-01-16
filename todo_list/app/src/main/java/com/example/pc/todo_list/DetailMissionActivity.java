package com.example.pc.todo_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.pc.todo_list.bean.Mission;
import com.example.pc.todo_list.fragment.DetailMissionFragment;

/**
 * Created by PC on 1/16/2017.
 */
public class DetailMissionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);
        //get data
        Mission mission = getIntent().getBundleExtra("bundle").getParcelable("mission");

        //set data
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //display list mission
        DetailMissionFragment fr = new DetailMissionFragment();
        ft.replace(R.id.frameDetailMission, fr, "fr1");
        fr.setArguments(getIntent().getBundleExtra("bundle"));
        ft.addToBackStack("fr1");
        ft.commit();

        ImageButton btnBack = (ImageButton) findViewById(R.id.imgBack);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.imgBack) finish();
    }
}
