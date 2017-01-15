package com.example.pc.todo_list.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pc.todo_list.MainActivity;
import com.example.pc.todo_list.R;
import com.example.pc.todo_list.adapter.SpinnerToolbarAdapter;
import com.example.pc.todo_list.bean.Mission;
import com.example.pc.todo_list.bean.TypeList;
import com.example.pc.todo_list.database.MissionDAO;
import com.example.pc.todo_list.database.TypeListDAO;
import com.example.pc.todo_list.service.BaoThuc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by HCD-Fresher057 on 1/11/2017.
 */

public class AddMissionFragment extends Fragment implements View.OnClickListener{

    EditText edNhiemVu;
    TextView tvDate;
    TextView tvTime;
    ImageButton btnAddMission;
    Spinner spinnerListType;
    MissionDAO missionDAO;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission_detail,container,false);
        missionDAO = new MissionDAO(getActivity());
        setupSpinnerToolbar(view);
        setButtonEvent(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddMission:
                Log.d("TEST","click button add");
                OnClickBtnAddMission(view);
                break;
            case R.id.tvNgayHethan:
                OnClickDatePicker(view);
                break;
            case R.id.tvGioHetHan:
                OnClickTimePicker(view);
                break;
            default: Log.d("TEST","click sai nut roi");
        }
    }

    private void setButtonEvent(View view) {
        edNhiemVu = (EditText) view.findViewById(R.id.edNhiemVu);
        tvDate = (TextView) view.findViewById(R.id.tvNgayHethan);
        tvTime = (TextView) view.findViewById(R.id.tvGioHetHan);
        btnAddMission = (ImageButton) view.findViewById(R.id.btnAddMission);
        //
        edNhiemVu.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        btnAddMission.setOnClickListener(this);
    }

    public void setupSpinnerToolbar(View view) {
        //get type list
        spinnerListType = (Spinner) view.findViewById(R.id.spinnerDanhSach);
        TypeListDAO typeListDAO = new TypeListDAO(getActivity());
        ArrayList<TypeList> arraySpinnerToolBar = (ArrayList<TypeList>) typeListDAO.getAllType();

        //set data into spinenr
        SpinnerToolbarAdapter adapterSpinnerToolBar = new SpinnerToolbarAdapter(getActivity(), android.R.layout.simple_spinner_item, arraySpinnerToolBar,2);
        adapterSpinnerToolBar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListType.setAdapter(adapterSpinnerToolBar);
    }



    private void OnClickBtnAddMission(View view) {
        //add new mission
        String _mision = edNhiemVu.getText().toString();
        String _date = tvDate.getText().toString();
        String _time = tvTime.getText().toString();
        int id_type = ((TypeList)spinnerListType.getSelectedItem()).getId();
        Mission mission = new Mission(0,_time,id_type,_date,0,_mision);
        long check = missionDAO.addMission(mission);

        // reset alarm
        BaoThuc.getInstance().baothuc(getContext());
        //display list mission
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    private void OnClickTimePicker(View view) {
        int hour;
        int minute;

        // dat time cho dialog-time neu textview da co time
        if(tvTime.getText().equals("")) {
            Calendar mcurrentTime = Calendar.getInstance();
            hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            minute = mcurrentTime.get(Calendar.MINUTE);
        }else{
            String getTime = tvTime.getText().toString();
            hour = Integer.parseInt(getTime.split(":")[0]);
            minute = Integer.parseInt(getTime.split(":")[1]);
        }

        TimePickerDialog mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvTime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time

        mTimePicker.setTitle("Chọn giờ ");
        mTimePicker.show();
    }

    private void OnClickDatePicker(View view) {
        final Calendar myCalendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        // dat date cho dialog-date neu textview da co date
        if(tvDate.getText()!=null)
            try {
                myCalendar.setTime(df.parse(tvDate.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        final DatePickerDialog date = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                //update date into textview
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tvDate.setText(sdf.format(myCalendar.getTime()));
                tvTime.setVisibility(View.VISIBLE);
            }

        }, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));

        date.setTitle("Chọn ngày");
        date.show();
    }


}
