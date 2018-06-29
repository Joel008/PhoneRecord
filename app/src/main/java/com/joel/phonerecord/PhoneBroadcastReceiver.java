package com.joel.phonerecord;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by asus on 2018/6/28.
 */

public class PhoneBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Intent service = new Intent(context, RecordService.class);
        context.startService(service);   //启动服务
    }
}
