package com.example.nazia_000.account.homePack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nazia_000.account.R;
import com.example.nazia_000.account.classPack.RequestClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class doRequestActivity extends AppCompatActivity {

    private FirebaseAuth doRequAuth;
    private DatabaseReference doRequRef;

    private EditText NamedoRequ,NmbrdoRequ,AmountdoRequ;

    private Spinner doRequStatusSpinner,doRequBloodGrpSpinner;
    private Button doreqbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_request);

        NamedoRequ = findViewById(R.id.doRequName);
        NmbrdoRequ = findViewById(R.id.doRequNmbr);
        AmountdoRequ = findViewById(R.id.doRequAmount);

        Handler handler = new Handler();
        doreqbtn = findViewById(R.id.doRequButton);
        doreqbtn.setOnClickListener(handler);

        doRequStatusSpinner = findViewById(R.id.doRequStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Request_Status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doRequStatusSpinner.setAdapter(adapter);
        doRequStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        doRequBloodGrpSpinner = findViewById(R.id.doRequBloodGrp);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Blood_Group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doRequBloodGrpSpinner.setAdapter(adapter1);
        doRequBloodGrpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void reqSend(){

        String name  = NamedoRequ.getText().toString().trim();
        String nmbr = NmbrdoRequ.getText().toString().trim();
        String amount = AmountdoRequ.getText().toString().trim();
        String grp = (String) doRequBloodGrpSpinner.getSelectedItem();
        String stat = (String) doRequStatusSpinner.getSelectedItem();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String doReqId = firebaseAuth.getUid();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        doRequRef = firebaseDatabase.getReference().child("Requests").child(doReqId);
        RequestClass requestClass = new RequestClass(name,nmbr,grp,amount,stat);
        doRequRef.setValue(requestClass);
        Toast.makeText(doRequestActivity.this,"Profile updated",Toast.LENGTH_SHORT).show();
    }

    class Handler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            reqSend();
        }
    }
}
