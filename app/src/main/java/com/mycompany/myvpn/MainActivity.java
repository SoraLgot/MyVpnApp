package com.mycompany.myvpn;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int VPN_REQUEST_CODE = 100;
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
                if (!isConnected) {
                    Intent intent = VpnService.prepare(MainActivity.this);
                    if (intent != null) {
                        startActivityForResult(intent, VPN_REQUEST_CODE);
                    } else {
                        onActivityResult(VPN_REQUEST_CODE, Activity.RESULT_OK, null);
                    }
                } else {
                    stopVpn();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VPN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            startVpn();
        } else {
            statusText.setText("РАЗРЕШЕНИЕ ОТКЛОНЕНО");
        }
    }

    private void startVpn() {
        Intent intent = new Intent(this, MyVpnService.class);
        startService(intent);
        isConnected = true;
        statusText.setText("ПОДКЛЮЧЁН");
        connectButton.setText("ОТКЛЮЧИТЬСЯ");
    }

    private void stopVpn() {
        Intent intent = new Intent(this, MyVpnService.class);
        intent.setAction("STOP");
        startService(intent);
        isConnected = false;
        statusText.setText("НЕ ПОДКЛЮЧЁН");
        connectButton.setText("ПОДКЛЮЧИТЬСЯ");
    }
}
