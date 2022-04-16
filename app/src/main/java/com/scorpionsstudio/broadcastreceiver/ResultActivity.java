package com.scorpionsstudio.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.scorpionsstudio.broadcastreceiver.broadcasters.BatteryBroadcaster;
import com.scorpionsstudio.broadcastreceiver.broadcasters.CustomBroadcast;
import com.scorpionsstudio.broadcastreceiver.broadcasters.WiFiRTTStateReceiver;

public class ResultActivity extends AppCompatActivity {

    TextView data_text, custom_text;
    BatteryBroadcaster batteryBroadcasterInstance;
    WiFiRTTStateReceiver wiFiRTTStateReceiverInstance;
    CustomBroadcast customBroadcastInstance;
    private int selectedItem;

    private Button returnToMain;

    private Switch switch_block_wifi;

    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        if(intent!=null){
            selectedItem = Integer.parseInt(intent.getStringExtra("selected_item"));
        }

        data_text = findViewById(R.id.data_text);
        custom_text = findViewById(R.id.custom_text);
        returnToMain = findViewById(R.id.returnToMain);


        switch_block_wifi = findViewById(R.id.switch_block_wifi);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if(selectedItem==2){

            customBroadcastInstance = new CustomBroadcast(custom_text, selectedItem);
            registerReceiver(customBroadcastInstance, new IntentFilter("spt.custom"));

            String payload = getIntent().getStringExtra("payload");

            Intent broadcastIntent = new Intent("spt.custom");
            broadcastIntent.putExtra("spt.payload", payload);
            sendBroadcast(broadcastIntent);

            batteryBroadcasterInstance = new BatteryBroadcaster(data_text);
            registerReceiver(batteryBroadcasterInstance, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        }

        switch_block_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                wifiManager.setWifiEnabled(isChecked);
            }
        });





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
        if(selectedItem==0){
            custom_text.setVisibility(View.GONE);
            customBroadcastInstance = new CustomBroadcast(data_text, selectedItem);
            registerReceiver(customBroadcastInstance, new IntentFilter("spt.custom"));

            String payload = getIntent().getStringExtra("payload");

            Intent broadcastIntent = new Intent("spt.custom");
            broadcastIntent.putExtra("spt.payload", payload);
            sendBroadcast(broadcastIntent);
        }
        if(selectedItem==1){
            switch_block_wifi.setVisibility(View.VISIBLE);
            custom_text.setVisibility(View.GONE);
            wiFiRTTStateReceiverInstance = new WiFiRTTStateReceiver(data_text, switch_block_wifi);
            registerReceiver(wiFiRTTStateReceiverInstance, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(selectedItem==0){
            unregisterReceiver(customBroadcastInstance);
        }
        else if(selectedItem==2){
            unregisterReceiver(batteryBroadcasterInstance);
        }else{
            unregisterReceiver(wiFiRTTStateReceiverInstance);
        }


    }

}