package com.scorpionsstudio.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomBroadcastActivity extends AppCompatActivity {

    private Button returnToMain, broadcast;
    private EditText message;
    private TextView customText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_broadcast);


        returnToMain = findViewById(R.id.returnToMain);
        broadcast = findViewById(R.id.broadcast);
        message = findViewById(R.id.message);
        customText = findViewById(R.id.customText);

        int selected = Integer.parseInt(getIntent().getStringExtra("selected_item"));
        if(selected==1){
            String temp = "Type Battery Percentage";
            customText.setText(temp);
        }

        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResultActivity();
            }
        });

    }


    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openResultActivity(){

        Intent intent = getIntent();
        int selectedItem = Integer.parseInt(intent.getStringExtra("selected_item"));
        intent = new Intent(this, ResultActivity.class);

        intent.putExtra("selected_item", selectedItem+"");
        intent.putExtra("payload", message.getText().toString());
        startActivity(intent);
    }
}