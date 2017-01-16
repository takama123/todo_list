package com.example.pc.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pc.todo_list.bean.TypeList;
import com.example.pc.todo_list.database.TypeListDAO;

/**
 * Created by PC on 1/17/2017.
 */
public class AddActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_type_list_layout);

        final EditText edNameTypeList = (EditText) findViewById(R.id.edNameTypeList);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        Button btnAdd = (Button) findViewById(R.id.btnAddTypeList);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TypeListDAO(getApplicationContext()).addType(new TypeList(0,edNameTypeList.getText().toString()));
                Intent intent = new Intent(AddActivity.this,DisplayTypeListActivity.class);
                startActivity(intent);
            }
        });

    }
}
