package com.scorpionsstudio.broadcastreceiver.broadcasters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class CustomBroadcast extends BroadcastReceiver {

    TextView data_text;
    int ident;

    public CustomBroadcast(TextView data_text, int _ident){
        this.data_text = data_text;
        ident = _ident;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra("spt.payload");
        if(ident==2){
            String temp = "Given Battery Percentage: "+text+"%";
            data_text.setText(temp);
        }else{
            data_text.setText(text);
        }

    }
}
