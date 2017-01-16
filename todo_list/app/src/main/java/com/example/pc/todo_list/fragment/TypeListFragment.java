package com.example.pc.todo_list.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.pc.todo_list.R;
import com.example.pc.todo_list.adapter.TypeListAdapter;
import com.example.pc.todo_list.bean.TypeList;
import com.example.pc.todo_list.database.MissionDAO;
import com.example.pc.todo_list.database.TypeListDAO;

import java.util.ArrayList;

/**
 * Created by HCD-Fresher057 on 1/11/2017.
 */

public class TypeListFragment extends Fragment implements TypeListAdapter.TypeListClick {

    TypeListDAO typeListDAO;
    View view;
    RecyclerView recyclerView;
    LinearLayout linearLayoutNothing;
    TypeListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        typeListDAO = new TypeListDAO(getContext());

        view = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listMain);
        linearLayoutNothing = (LinearLayout) view.findViewById(R.id.linearNothing);

        // set list mission into recycleview
        displayTypeList();
        return view;
    }

    private void displayTypeList() {
        // get list mission
        ArrayList<TypeList> arrayMisson = (ArrayList<TypeList>) typeListDAO.getAllType();
        arrayMisson.remove(arrayMisson.size()-1);
        //set
        ///
        if(arrayMisson.size()!=0) {
            adapter = new TypeListAdapter(getContext(), arrayMisson);
            adapter.setItemClick(this);
            RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(lm);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            linearLayoutNothing.setVisibility(View.GONE);
        }
    }

    @Override
    public void btnDeleteClick(final int id_typelist) {
        new AlertDialog.Builder(getContext())
                .setTitle("Xóa danh sách")
                .setMessage("Bạn có chắc chắn muốn xóa không ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        typeListDAO.deleteTypeList(new TypeList(id_typelist,""));
                        new MissionDAO(getContext()).deleteMissionByIdType(id_typelist);
                        displayTypeList();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public void btnEditClick(int id_mission) {

    }
}
