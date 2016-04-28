package com.example.cstuser.soccer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class SMS extends Activity {

    private Spinner contactList;
    String phoneNumber;
    private Button sendSMS, sendNotif, returnMenuBtn;
    int notificationID;
    String[] contactsArray;

    public SMS() {
        this.notificationID = 1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_main);

        addItemsOnContactsList();


        sendSMS = (Button) findViewById(R.id.btnSendSMS);
        sendNotif = (Button) findViewById(R.id.btnNotif);
        returnMenuBtn = (Button) findViewById(R.id.returnMenuBtn);

        sendSMS.setOnClickListener(listener);
        sendNotif.setOnClickListener(listener);
        returnMenuBtn.setOnClickListener(listener);

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
        notif.setLatestEventInfo(this, contactList.getSelectedItem().toString(), "You message has been sent", pendingIntent);
        notif.vibrate = new long[]{100, 250, 100, 500};
        nm.notify(this.notificationID, notif);
    }




    // add items into spinner dynamically
    public void addItemsOnContactsList() {

        contactList = (Spinner) findViewById(R.id.contactSpinner);
        contactsArray = getResources().getStringArray(R.array.contacts_array);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, contactsArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactList.setAdapter(dataAdapter);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            if(v == sendSMS){
                switch (contactList.getSelectedItem().toString()){
                    case "Anthony Funiciello": phoneNumber = "5147063444"; break;
                    case "Kevin Tsai": phoneNumber = "5145696589"; break;
                    case "Timothy Rodriguez": phoneNumber = "5142318867"; break;
                    case "Josh Pham": phoneNumber = "5145828471"; break;
                    case "Mayurran Selliah": phoneNumber = "5146598139"; break;
                    case "Vivianne Yu": phoneNumber = "5146598139"; break;
                    case "David Liu": phoneNumber = "5146598139"; break;
                    case "Jason Tiu": phoneNumber = "5146598139"; break;
                    case "Sheriff Ghaffar": phoneNumber = "5146598139"; break;
                    case "Daniel Edery": phoneNumber = "5146598139"; break;
                    default: phoneNumber = "Invalid Contact"; break;
                }
                sendSMS(phoneNumber, "Hello my friends!");

            }else if(v == sendNotif){
                displayNotification();
            }
            else if(v == returnMenuBtn){
               finish();
            }
        }
    };



}
