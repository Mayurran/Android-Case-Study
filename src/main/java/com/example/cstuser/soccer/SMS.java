package com.example.cstuser.soccer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class SMS extends Activity {
    int notificationID;

    public SMS() {
        this.notificationID = 1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_main);
    }

    public void onClick(View v) {
        sendSMS("5147063444", "Hello my friends!");
        sendSMS("5145696589", "Hello my friends!");
        sendSMS("5142318867", "Hello my friends!");
        sendSMS("5145828471", "Hello my friends!");
        sendSMS("5146598139", "Hello my friends!");
        displayNotification();
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, null, null);
    }

    protected void displayNotification() {
        Intent i = new Intent(this, Notification.class);
        i.putExtra("notificationID", this.notificationID);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
        NotificationManager nm =  (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notif = new Notification(R.mipmap.ic_launcher, "Notification Sent", System.currentTimeMillis());
        notif.setLatestEventInfo(this, "System Alarm", "You message has been sent", pendingIntent);
        notif.vibrate = new long[]{100, 250, 100, 500};
        nm.notify(this.notificationID, notif);
    }
}
