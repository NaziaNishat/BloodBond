package com.example.nazia_000.account.homePack;

import android.content.Intent;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nazia_000.account.Adapter.UserAdapter;
import com.example.nazia_000.account.R;
import com.example.nazia_000.account.classPack.ProfilesClass;
import com.example.nazia_000.account.mapPack.MyLocation;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class searchActivity extends AppCompatActivity {

    private DatabaseReference searchRef;

    private ListView listView;
    private ArrayList<ProfilesClass> profilesList;

    private Spinner listSearchSpin;
    private Button toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = findViewById(R.id.userListView);
        listSearchSpin = findViewById(R.id.spinnerSearchList);
        toggleBtn = findViewById(R.id.toggleList);

        Handler handler = new Handler();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Blood_Group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listSearchSpin.setAdapter(adapter);
        listSearchSpin.setOnItemSelectedListener(handler);

        profilesList = new ArrayList<ProfilesClass>();
        searchRef = FirebaseDatabase.getInstance().getReference("Users");


        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyLocation.class));
            }
        });

    }

    class Handler implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            profilesList.clear();
            String s = (String) listSearchSpin.getSelectedItem();

            Query query = FirebaseDatabase.getInstance().getReference("Users")
                    .orderByChild("blood_group")
                    .equalTo(s);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        ProfilesClass profilesClass = ds.getValue(ProfilesClass.class);
                        profilesList.add(profilesClass);
                    }

                    UserAdapter userAdapter = new UserAdapter(searchActivity.this,profilesList);
                    listView.setAdapter(userAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
