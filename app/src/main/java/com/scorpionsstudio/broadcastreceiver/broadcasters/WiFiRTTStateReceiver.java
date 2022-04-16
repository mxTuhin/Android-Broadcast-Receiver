package com.scorpionsstudio.broadcastreceiver.broadcasters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.TextView;


public class WiFiRTTStateReceiver extends BroadcastReceiver {

    private TextView data_text;

    public WiFiRTTStateReceiver(TextView data_text){
        this.data_text = data_text;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN);
        String temp;
        switch (wifiStateExtra){
            case WifiManager.WIFI_STATE_ENABLED:
                temp = "Wifi Is Enabled";
                data_text.setText(temp);
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                temp = "Wifi Is Disabled";
                data_text.setText(temp);
                break;
        }
    }
}
