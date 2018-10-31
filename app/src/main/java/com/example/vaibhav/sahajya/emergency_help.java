package com.example.vaibhav.sahajya;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.HashMap;
import java.util.Map;

public class emergency_help extends AppCompatActivity {

    // Constants for the notification actions buttons.
    private static final String ACTION_UPDATE_NOTIFICATION ="com.android.example.notifyme.ACTION_UPDATE_NOTIFICATION";
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID ="primary_notification_channel";
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotifyManager;

    //private NotificationReceiver mReceiver = new NotificationReceiver();
    private EditText contact;
    private EditText emergency_text;
    private CheckBox yes_box;
    private CheckBox no_box;
    private Button submit_emergency;
    String[] SPINNERLISTEH = {"Manual", "Automatically"};
    private FirebaseFirestore firebaseFirestore;
    int flag = 0;
    /*public void checkbox_clicked(View view)
    {
        if (yes_box.isChecked())
        {
            String airlift = "Yes";
        }

        if (no_box.isChecked())
        {   flag=1;
            String airlift ="no";
        }
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_help);
        firebaseFirestore = FirebaseFirestore.getInstance();
        contact = (EditText) findViewById(R.id.contactid);
        emergency_text = (EditText) findViewById(R.id.emergency_context);
        yes_box = (CheckBox) findViewById(R.id.yesid);
        no_box = (CheckBox) findViewById(R.id.noid);
        submit_emergency = (Button) findViewById(R.id.submit_emergency);
        createNotificationChannel();
        yes_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(emergency_help.this, "You have selected yes", Toast.LENGTH_SHORT).show();
            }
        });

        no_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
            }
        });
        final ArrayAdapter<String> arrayAdaptereh = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLISTEH);
        final MaterialBetterSpinner materialBetterSpinnereh = (MaterialBetterSpinner) findViewById(R.id.location);
        submit_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendNotification();

                String contact_db = contact.getText().toString();
                String emergency_textdb = emergency_text.getText().toString();
                String airliftdb;
                if (flag == 0) {
                    airliftdb = "yes";
                    //notificationcall();
                    sendNotification();
                } else {
                    airliftdb = "no";

                }
                final Map<String, String> emergency_help_map = new HashMap<>();
                emergency_help_map.put("Contact", contact_db);
                emergency_help_map.put("Emergency", emergency_textdb);

                emergency_help_map.put("Airlift", airliftdb);
                firebaseFirestore.collection("Emergency Help").add(emergency_help_map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(emergency_help.this, "Your response has been submitted", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(emergency_help.this, "Error adding your details to database", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });
            }
        });
    }

    /*public void notificationcall() {
       /* NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /*CharSequence name = getString(R.string.common_google_play_services_notification_channel_name);
            String description = getString(R.id.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);

            notificationChannel.setDescription("Channel Description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


            NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this, "M_CH_ID");
            notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_notification_icon)
                    .setContentTitle("Airlift Notification")
                    .setAutoCancel(true)
                    .setContentText("Please stay calm, Help is on the way ")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationBuilder.build());
        }*/

    public void sendNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");


            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});

            mNotifyManager.createNotificationChannel(notificationChannel);

            // Create a NotificationChannel
        }
    }
    private NotificationCompat.Builder getNotificationBuilder(){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,PRIMARY_CHANNEL_ID)
                .setContentTitle("Emergency Help")
                .setContentText("Please stay calm , help is arriving ")
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setAutoCancel(true);
        return notifyBuilder;
    }
    }
