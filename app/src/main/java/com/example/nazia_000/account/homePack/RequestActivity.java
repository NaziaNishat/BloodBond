package com.example.nazia_000.account.homePack;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nazia_000.account.Adapter.RequestAdapter;
import com.example.nazia_000.account.R;
import com.example.nazia_000.account.classPack.RequestClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference requRef;
    private List<RequestClass> requestClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        listView = findViewById(R.id.requ_list_view);
        requRef = FirebaseDatabase.getInstance().getReference("Requests");
        requestClassList = new ArrayList<RequestClass>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        requRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    RequestClass requestClass = ds.getValue(RequestClass.class);
                    requestClassList.add(requestClass);
                }

                RequestAdapter requestAdapter = new RequestAdapter(RequestActivity.this,requestClassList);
                listView.setAdapter(requestAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


