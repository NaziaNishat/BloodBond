package com.example.nazia_000.account.entryPack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nazia_000.account.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_activity extends AppCompatActivity {

    private FirebaseAuth signupAuth;

    private ProgressDialog progressDialog;
    private Button submit_btn;
    private EditText mail_signup,pass_signup,con_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);

        signupAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        mail_signup = findViewById(R.id.signup_email);
        pass_signup = findViewById(R.id.signup_password);
        con_pass = findViewById(R.id.conPassField);
        submit_btn = findViewById(R.id.submit);

        Handler handler = new Handler();
        submit_btn.setOnClickListener(handler);

    }

    private void signupUser(){

        String email = mail_signup.getText().toString().trim();
        String password = pass_signup.getText().toString();
        String con_password = con_pass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Username is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(con_password)){
            Toast.makeText(this,"'Confirm Password' is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!password.matches(con_password)){
            Toast.makeText(this,"Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.length()<6){
            Toast.makeText(this,"Please enter at least 6-digits characters.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering user....");
        progressDialog.show();


        signupAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.cancel();
                        nxtState();
                    }
                    else{
                        progressDialog.cancel();
                        Toast.makeText(signup_activity.this, " " + task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void nxtState(){
        Intent intent = new Intent(signup_activity.this , ProfileActivity.class);
        startActivity(intent);
        }

    class Handler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.submit){
                //Toast.makeText(signup_activity.this,"sign",Toast.LENGTH_SHORT).show();
               signupUser();
            }
        }
    }
}
