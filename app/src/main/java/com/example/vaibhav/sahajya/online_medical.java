package com.example.vaibhav.sahajya;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class online_medical extends AppCompatActivity {

  //  private DatabaseReference mDatabase;
    //private ArrayList<MessageOBJ> msgList;
// ...
    private Button submit_anonymous;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_help);
        submit_anonymous = (Button)findViewById(R.id.button_anonymous);

        submit_anonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("shuru","nayi activity invoked");
                Intent intent = new Intent("com.example.vaibhav.askdoc");
                startActivity(intent);

            }
        });
      //  mDatabase = FirebaseDatabase.getInstance().getReference();
        //msgList = new ArrayList<MessageOBJ>();


        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    MessageOBJ ms = snapshot.getValue(MessageOBJ.class);
//
//                    if (!msgList.contains(ms)) {
//                        msgList.add(ms);
//                        Log.d("sickmsg",ms.toString());
//                    }
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
       // reference.addChildEventListener(new ChildEventListener() {
         //   @Override
           // public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              //  MessageOBJ ms = dataSnapshot.getValue(MessageOBJ.class);
             //   Log.d("sickmsg",ms.toString());
  //          }

            //@Override
            //public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            //}

          //  @Override
            //public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            //}

            //@Override
            //public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            //}

            //@Override
            //public void onCancelled(@NonNull DatabaseError databaseError) {

            //}
       // });

    }
}
