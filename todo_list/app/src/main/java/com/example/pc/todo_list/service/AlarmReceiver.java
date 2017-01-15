package com.example.pc.todo_list.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmReceiver extends BroadcastReceiver {

    String message;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        Intent in = new Intent(context,AlarmService.class);
        in.putExtras(bundle);
        context.startService(in);

    }
}
