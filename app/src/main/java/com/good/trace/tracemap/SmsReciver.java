package com.good.trace.tracemap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by lenovo on 2016/12/30.
 */

public class SmsReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[])object);
                String phone = msg.getOriginatingAddress();
                String content = msg.getMessageBody();
                if(content.equals("request")) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, MainActivity.loc, null,
                            null);
                } else if(content.substring(0,1).equals("(")) {
                    MainActivity.action(content);
                    Toast.makeText(context,"aaaaaaaaaaa",Toast.LENGTH_LONG).show();
                } else {

                }
            }
        }
    }

}