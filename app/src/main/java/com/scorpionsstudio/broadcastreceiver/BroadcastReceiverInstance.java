package com.scorpionsstudio.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class BroadcastReceiverInstance extends BroadcastReceiver {

    TextView data_text;

    public BroadcastReceiverInstance(TextView data_text){
        this.data_text = data_text;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int battery_percentage = intent.getIntExtra("level", 0);
        if(battery_percentage!=0){
            String tmp = battery_percentage+"%";
            data_text.setText(tmp);
        }
    }
}
