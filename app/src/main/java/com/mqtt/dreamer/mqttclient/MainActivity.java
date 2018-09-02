package com.mqtt.dreamer.mqttclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    //public static final String URL = "tcp://23.105.211.20:1883";
    public static final String URL = "tcp://192.168.169.101:1883";

    private String userName = "userName";

    private String password = "password";

    private String clientId = "clientId";
    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        findViewById(R.id.btn_conn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"you clicked button1",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean b = MqttManager.getInstance().creatConnect(URL, userName, password, clientId);
                       // Logger.d("isConnected: " + b);
                        Log.i(TAG, "run: connected");
                    }
                }).start();
            }
        });
        findViewById(R.id.btn_publish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MqttManager.getInstance().publish("test", 2, "hello".getBytes());
                        Log.i(TAG, "run: publish");
                    }
                }).start();
            }
        });
        findViewById(R.id.btn_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MqttManager.getInstance().subscribe("test", 2);
                        Log.i(TAG, "run: subcribe");
                    }
                }).start();
            }
        });
        findViewById(R.id.btn_discon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.i(TAG, "run: connected");
                            MqttManager.getInstance().disConnect();
                        } catch (MqttException e) {

                        }
                    }
                }).start();
            }
        });

    }

    @Subscribe
    public void onEvent(MqttMessage message) {
        //messageLogger.d(message.toString());
        Log.i(TAG, message.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
