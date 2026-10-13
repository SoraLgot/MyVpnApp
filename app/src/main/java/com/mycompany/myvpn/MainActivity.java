package com.mycompany.myvpn;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isConnected = false;
    private TextView statusText;
    private Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        connectButton = findViewById(R.id.connectButton);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isConnected = !isConnected;
                if (isConnected) {
                    statusText.setText("ПОДКЛЮЧЁН");
                    connectButton.setText("ОТКЛЮЧИТЬСЯ");
                } else {
                    statusText.setText("НЕ ПОДКЛЮЧЁН");
                    connectButton.setText("ПОДКЛЮЧИТЬСЯ");
                }
            }
        });
    }
}
