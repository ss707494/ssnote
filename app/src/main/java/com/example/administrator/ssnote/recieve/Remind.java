package com.example.administrator.ssnote.recieve;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.ssnote.service.RemindService;

/**
 * Created by Administrator on 2015/9/13.
 */
public class Remind extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, RemindService.class));
    }
}
