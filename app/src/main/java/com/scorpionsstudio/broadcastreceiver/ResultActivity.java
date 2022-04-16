package com.scorpionsstudio.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView data_text;
    BroadcastReceiverInstance broadcastReceiverInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        data_text = findViewById(R.id.data_text);
        broadcastReceiverInstance = new BroadcastReceiverInstance(data_text);
        registerReceiver(broadcastReceiverInstance, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiverInstance);
    }
}