package com.scorpionsstudio.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomBroadcast extends AppCompatActivity {

    private Button returnToMain, broadcast;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_broadcast);


        returnToMain = findViewById(R.id.returnToMain);
        broadcast = findViewById(R.id.broadcast);

        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        returnToMain.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("payload", message.getText().toString());
        startActivity(intent);
    }
}