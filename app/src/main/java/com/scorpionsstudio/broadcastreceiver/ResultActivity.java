package com.scorpionsstudio.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scorpionsstudio.broadcastreceiver.broadcasters.BatteryBroadcaster;
import com.scorpionsstudio.broadcastreceiver.broadcasters.WiFiRTTStateReceiver;

public class ResultActivity extends AppCompatActivity {

    TextView data_text;
    BatteryBroadcaster batteryBroadcasterInstance;
    WiFiRTTStateReceiver wiFiRTTStateReceiverInstance;
    private int selectedItem;

    private Button returnToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        if(intent!=null){
            selectedItem = Integer.parseInt(intent.getStringExtra("selected_item"));
        }

        data_text = findViewById(R.id.data_text);
        returnToMain = findViewById(R.id.returnToMain);
        if(selectedItem==2){
            batteryBroadcasterInstance = new BatteryBroadcaster(data_text);
            registerReceiver(batteryBroadcasterInstance, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        }


        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(selectedItem==1){
            wiFiRTTStateReceiverInstance = new WiFiRTTStateReceiver(data_text);
            registerReceiver(wiFiRTTStateReceiverInstance, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(selectedItem==2){
            unregisterReceiver(batteryBroadcasterInstance);
        }else{
            unregisterReceiver(wiFiRTTStateReceiverInstance);
        }

    }
}