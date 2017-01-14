package com.example.pc.todo_list.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.todo_list.R;
import com.example.pc.todo_list.bean.Mission;

import java.util.List;


/**
 * Created by HCD-Fresher057 on 1/11/2017.
 */

public class ListMissionAdapter extends RecyclerView.Adapter<ListMissionAdapter.ViewHodler> {

    List<Mission> listMission;
    Context context;
    MyItemClick itemClick;
    int id_mission;

    public ListMissionAdapter(Context context, List<Mission> listMission) {
        this.context = context;
        this.listMission = listMission;
    }

    public ListMissionAdapter() {
        super();
    }

    public void setItemClick(MyItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mission_item, parent,false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        Mission mission = listMission.get(position);
        this.id_mission = mission.get_id();

        holder.tvTenNhiemVu.setText(mission.getM_ten_nhiem_vu());
        holder.tvDateTime.setText(mission.getM_gio_het_han() + "  " + mission.getM_ngay_het_han());
        if(mission.getM_status()==1){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return listMission.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        TextView tvTenNhiemVu, tvDateTime;
        CheckBox checkBox;
        LinearLayout ln;

        public ViewHodler(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_item_list);
            tvTenNhiemVu = (TextView) itemView.findViewById(R.id.tvTenNhiemVu);
            tvDateTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            ln = (LinearLayout) itemView.findViewById(R.id.linearlayout1);

            ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.layoutClick(id_mission);
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.checkBoxClick(id_mission);
                }
            });
        }
    }

    public interface MyItemClick {
        void checkBoxClick(int id_mission);
        void layoutClick(int id_mission);

    }
}
