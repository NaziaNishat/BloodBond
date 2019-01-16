package com.example.nazia_000.account.navPack;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nazia_000.account.R;
import com.example.nazia_000.account.classPack.ProfilesClass;
import com.example.nazia_000.account.classPack.RequestClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class navProfile extends AppCompatActivity {

    private DatabaseReference myProfRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        myProfRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());

        setProfile();

    }

    private void setProfile(){

        TextView myNameTxt =  findViewById(R.id.myProfName);
        TextView myNmbrTxt = findViewById(R.id.myNmbr);
        TextView myGrpTxt = findViewById(R.id.myBldGrp);
        TextView myEmailTxt = findViewById(R.id.myEmail);
        TextView myAdrsTxt = findViewById(R.id.myAddress);

        myNameTxt.setText("Username : "+user.getDisplayName());
        myNmbrTxt.setText("Phone Number : "+user.getPhoneNumber());
        myGrpTxt.setText("Blood Group : "+myProfRef.orderByChild("blood_group"));
        myEmailTxt.setText("E-mail : "+user.getEmail());
        myAdrsTxt.setText("Address : "+myProfRef.orderByChild("address"));

    }
}
