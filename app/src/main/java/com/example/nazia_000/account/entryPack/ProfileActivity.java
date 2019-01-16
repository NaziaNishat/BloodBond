package com.example.nazia_000.account.entryPack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nazia_000.account.classPack.LocationClass;
import com.example.nazia_000.account.mapPack.MyLocation;
import com.example.nazia_000.account.R;
import com.example.nazia_000.account.classPack.ProfilesClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth proAuth;
    private DatabaseReference proReference;

    private Spinner proSpinner;
    private Spinner proReadySpinner;
    private Button savepro;
    private EditText namePro,agePro,nmbrPro,sendpro;

    private MyLocation myLocation;

    private boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myLocation = new MyLocation();

        proAuth = FirebaseAuth.getInstance();

        namePro = findViewById(R.id.proName);
        agePro = findViewById(R.id.proAge);
        nmbrPro = findViewById(R.id.proContact);
        savepro = findViewById(R.id.proSave);
        sendpro = findViewById(R.id.proSend);

        proSpinner = findViewById(R.id.proBldGrp);
        proReadySpinner = findViewById(R.id.proYesNo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Blood_Group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterYesNo = ArrayAdapter.createFromResource(this, R.array.ReadyToDonate, android.R.layout.simple_spinner_item);
        adapterYesNo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        proSpinner.setAdapter(adapter);
        proReadySpinner.setAdapter(adapterYesNo);

        HandlerSpinner handlerSpinner = new HandlerSpinner();
        proSpinner.setOnItemSelectedListener(handlerSpinner);

        HandlerSpinner1 handlerSpinner1 = new HandlerSpinner1();
        proReadySpinner.setOnItemSelectedListener(handlerSpinner1);

        Handler handler = new Handler();
        savepro.setOnClickListener(handler);

    }

    private void addProfile(){
        String username = namePro.getText().toString().trim();
        String age = agePro.getText().toString().trim();
        String nmbr = nmbrPro.getText().toString().trim();
        String address = sendpro.getText().toString().trim();
        String grp = (String) proSpinner.getSelectedItem();
        String rdy = (String) proReadySpinner.getSelectedItem();

        if(TextUtils.isEmpty(username) && TextUtils.isEmpty(age) && TextUtils.isEmpty(nmbr) && TextUtils.isEmpty(address)){
            Toast.makeText(ProfileActivity.this,"Fill up all field",Toast.LENGTH_SHORT).show();
        }
        else{

            ProfilesClass profilesClass = new ProfilesClass();


            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            String ref_id = proAuth.getUid();
            proReference = firebaseDatabase.getReference().child("Users").child(ref_id);
            profilesClass = new ProfilesClass(username,age,nmbr,grp,rdy,address);

            proReference.setValue(profilesClass);
            Toast.makeText(ProfileActivity.this,"Profile updated",Toast.LENGTH_SHORT).show();
        }

    }

    class Handler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.proSave){
                addProfile();
            }
        }
    }

    class HandlerSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class HandlerSpinner1 implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}
