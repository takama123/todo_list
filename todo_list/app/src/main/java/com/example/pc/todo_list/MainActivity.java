package com.example.pc.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.pc.todo_list.adapter.SpinnerToolbarAdapter;
import com.example.pc.todo_list.bean.CompareByDate;
import com.example.pc.todo_list.bean.Mission;
import com.example.pc.todo_list.bean.TypeList;
import com.example.pc.todo_list.database.MissionDAO;
import com.example.pc.todo_list.database.TypeListDAO;
import com.example.pc.todo_list.fragment.ListMissionFragment;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    ImageButton btnAdd;
    ImageView imgIcon;
    MissionDAO missionDAO;
    TypeListDAO typeListDAO;
    Spinner spinnerToolbar;
    FragmentManager fm;
    FragmentTransaction ft;
    ListMissionFragment fr;

    ArrayList<TypeList> arraySpinnerToolBar = new ArrayList<TypeList>();
    SpinnerToolbarAdapter adapterSpinnerToolBar = null;

    ArrayList<Mission> arrayMisson = new ArrayList<Mission>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidgetsToobar();
        setListMission();
        // setup btn add mission
        setupBtnAdd();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_mission_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setWidgetsToobar() {
        missionDAO = new MissionDAO(this);
        typeListDAO = new TypeListDAO(this);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup imageIcon in toolbar
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
        imgIcon.setImageResource(R.mipmap.todolist);

        // Setup spinner
        setupSpinnerToolbar();
    }

    private void setupBtnAdd() {
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
    }

    public void setupSpinnerToolbar() {
        spinnerToolbar = (Spinner) findViewById(R.id.spinner);

        //get type list
        if (typeListDAO.getAllType().size() == 0) {
            Log.d("TEST", "tao moi du lieu");
            TypeList t = new TypeList(1, "Mặc định");
            typeListDAO.addType(t);
            typeListDAO.addType(new TypeList(0, "Kết thúc"));
        }
        arraySpinnerToolBar = (ArrayList<TypeList>) typeListDAO.getAllType();

        //set data into spinenr
        adapterSpinnerToolBar = new SpinnerToolbarAdapter(this, android.R.layout.simple_spinner_item, arraySpinnerToolBar,1);
        adapterSpinnerToolBar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToolbar.setAdapter(adapterSpinnerToolBar);

        // set Click event for spinner
        addEventOfSpinnerToolbar();
    }

    public void addEventOfSpinnerToolbar() {
        spinnerToolbar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //reload list by type
                setListMission();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void loadTypeList() {
        //xóa danh sách cũ
        arraySpinnerToolBar.clear();
        //lấy danh sách mới từ Catalog chọn trong Spinner
        arraySpinnerToolBar.addAll(typeListDAO.getAllType());
        //cập nhật lại ListView
        adapterSpinnerToolBar.notifyDataSetChanged();
    }

    public void addTypeList() {

    }

    private void setListMission() {

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        // get listMission
        TypeList typeList = ((TypeList) spinnerToolbar.getSelectedItem());
        arrayMisson = (ArrayList<Mission>) missionDAO.getListMissonByIdTypeList(typeList.getId());

        // sap xep theo date time
        Collections.sort(arrayMisson,new CompareByDate());
//        for (Mission m: arrayMisson
//             ) {
//            Log.d("test",m.getM_ten_nhiem_vu()+" :: "
//                    +"month:"+m.convertToCalendar().getTime().getMonth()+" :"
//                    +"day:"+m.convertToCalendar().getTime().getDate()+" :"
//                    +"hour:"+m.convertToCalendar().getTime().getHours()+" :"
//                    +"minute:"+m.convertToCalendar().getTime().getMinutes()+" :"
//            );
//
//        }
        // display
        Bundle b = new Bundle();
        b.putParcelableArrayList("listMission", arrayMisson);
        b.putString("list_name", typeList.getKieu_danh_sach());
        //
        fr = new ListMissionFragment();
        fr.setArguments(b);
        ft.replace(R.id.frameListMission, fr, "fr1");
        ft.addToBackStack("fr1");
        Log.d("TEST", "setListMission");
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,MissionActivity.class);
        startActivity(intent);
    }
}

