package com.example.pc.todo_list.fragment;

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

import com.example.pc.todo_list.R;
import com.example.pc.todo_list.adapter.ListMissionAdapter;
import com.example.pc.todo_list.bean.Mission;

import java.util.ArrayList;

/**
 * Created by HCD-Fresher057 on 1/11/2017.
 */

public class ListMissionFragment extends Fragment implements ListMissionAdapter.MyItemClick {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listMain);
        LinearLayout linearLayoutNothing = (LinearLayout) view.findViewById(R.id.linearNothing);
        TextView tvNothing = (TextView) view.findViewById(R.id.tvNothing);

        String list_name = getArguments().getString("list_name"); //get list_name
        // get list mission
        ArrayList<Mission> listMissionByIdType = (ArrayList<Mission>) getArguments().get("listMission");
//        listMissionByIdType.sort();
        if(listMissionByIdType.size()!=0) {
            ListMissionAdapter adapter = new ListMissionAdapter(container.getContext(), listMissionByIdType);
            adapter.setItemClick(this);
            RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
//            new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(lm);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            linearLayoutNothing.setVisibility(View.GONE);
        }else{
            tvNothing.setText(list_name);
            recyclerView.setVisibility(View.GONE);
            linearLayoutNothing.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void checkBoxClick(int id_mission) {
        Log.d("Yen","checkBox");

    }

    @Override
    public void layoutClick(int id_mission) {
        Log.d("Yen","layoutClick");
    }
}
