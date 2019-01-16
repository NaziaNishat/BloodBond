package com.example.nazia_000.account.navPack;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nazia_000.account.R;
import com.example.nazia_000.account.classPack.RequestClass;
import com.example.nazia_000.account.homePack.RequestActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class navMyRequests extends AppCompatActivity {

    private DatabaseReference myReqRef;
    private FirebaseUser firebaseUser;

    private List<RequestClass> myReqList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_my_requests);

        myReqRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        final String userKey = FirebaseAuth.getInstance().getCurrentUser().getUid();

        myReqRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    RequestClass requestClass = ds.getValue(RequestClass.class);
                    if(myReqRef.getKey() == userKey){
                        myReqList.add(requestClass);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
