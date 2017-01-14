package com.example.pc.todo_list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pc.todo_list.R;
import com.example.pc.todo_list.bean.TypeList;

import java.util.List;

/**
 * Created by HCD-Fresher057 on 1/12/2017.
 */

public class SpinnerToolbarAdapter extends ArrayAdapter<TypeList> {

    Context context;
    List<TypeList> listType;
    int i;

    public SpinnerToolbarAdapter(Context context, int resource, List<TypeList> objects, int kieuLayout) {
        super(context, resource, objects);
        this.listType = objects;
        this.context = context;
        this.i = kieuLayout;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        int layout = 0;
        if(i == 1)layout =  R.layout.spinner_adapter_item;
        else layout =  R.layout.spinner_adapter_item2;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.tvSpinnerAdapter);
        label.setText(listType.get(position).getKieu_danh_sach());

        return row;
    }
}