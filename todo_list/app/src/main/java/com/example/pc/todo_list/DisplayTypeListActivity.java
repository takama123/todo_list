package com.example.pc.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.pc.todo_list.fragment.TypeListFragment;

/**
 * Created by PC on 1/17/2017.
 */
public class DisplayTypeListActivity
        extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_typelist_layout);
        displayAllTypeLists();
    }

    private void displayAllTypeLists() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //display list mission
        TypeListFragment fr = new TypeListFragment();
        ft.replace(R.id.frameListTypeMission, fr, "fr1");
        ft.addToBackStack("fr1");
        ft.commit();

        ImageButton btnBack = (ImageButton) findViewById(R.id.imgBack2);
        btnBack.setOnClickListener(this);
        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAddTypeList2);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.imgBack2) finish();
        if(view.getId() == R.id.btnAddTypeList2) {
            Intent intent = new Intent(this,AddActivity.class);
            startActivity(intent);
        }
    }
}
