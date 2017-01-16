package com.example.pc.todo_list.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.todo_list.DetailMissionActivity;
import com.example.pc.todo_list.R;
import com.example.pc.todo_list.adapter.ListMissionAdapter;
import com.example.pc.todo_list.bean.CompareByDate;
import com.example.pc.todo_list.bean.Mission;
import com.example.pc.todo_list.database.MissionDAO;
import com.example.pc.todo_list.service.BaoThuc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by HCD-Fresher057 on 1/11/2017.
 */

public class ListMissionFragment extends Fragment implements ListMissionAdapter.MyItemClick {

    MissionDAO missionDAO;
    View view;
    RecyclerView recyclerView;
    LinearLayout linearLayoutNothing;
    TextView tvNothing;
    ListMissionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        missionDAO = new MissionDAO(getContext());
        view = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listMain);
        linearLayoutNothing = (LinearLayout) view.findViewById(R.id.linearNothing);
        tvNothing = (TextView) view.findViewById(R.id.tvNothing);


        // set list mission into recycleview
        displayListMissions();
        return view;
    }

    private void displayListMissions() {
        String list_name = getArguments().getString("list_name"); //get list_name
        int id_type = getArguments().getInt("id_type_list");
        // get list mission
        ArrayList<Mission> arrayMisson = (ArrayList<Mission>) missionDAO.getListMissonByIdTypeList(id_type);

        // sap xep theo date time
        Collections.sort(arrayMisson,new CompareByDate());
        //set
        ///
        if(arrayMisson.size()!=0) {
            adapter = new ListMissionAdapter(getContext(), arrayMisson);
            adapter.setItemClick(this);
            RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(lm);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            linearLayoutNothing.setVisibility(View.GONE);
        }else{
            tvNothing.setText(list_name);
            recyclerView.setVisibility(View.GONE);
            linearLayoutNothing.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void checkBoxClick(int id_mission) {
        String list_name = getArguments().getString("list_name"); //get list_name
        if(!("Mặc định").endsWith(list_name)) {
            //reset id_danhsach of mission
            Mission mission = missionDAO.getMissionByIdMission(id_mission);
            mission.setM_id_danhsach(2);
            mission.setM_status(1);
            missionDAO.updateMission(mission);
            //reload list mission
            displayListMissions();
            //reset bao thuc
            new BaoThuc().baothuc(getContext());
        }else{
            Log.d("test","click click click ...");
        }
    }

    @Override
    public void layoutClick(int id_mission) {
        // display detail mission activity
        Mission mission = missionDAO.getMissionByIdMission(id_mission);
        Intent intent = new Intent(getActivity(),DetailMissionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("mission",mission);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }
}
