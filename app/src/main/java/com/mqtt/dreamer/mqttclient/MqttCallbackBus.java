package com.mqtt.dreamer.mqttclient;

import android.util.Log;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;


public class MqttCallbackBus implements MqttCallback {
    private String TAG= "MqttCallbackBus";
    @Override
    public void connectionLost(Throwable cause) {
        Log.i(TAG, cause.toString());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        Log.i(TAG, topic);
        Log.i(TAG, message.toString());
        EventBus.getDefault().post(message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

}
