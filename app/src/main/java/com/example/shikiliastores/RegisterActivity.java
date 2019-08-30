package com.example.shikiliastores;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText username, userpassword, confirmpassword, useremail;
    Button regbutton;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        findIds();
    }


    private void findIds() {
        username = (EditText) findViewById(R.id.etUserNamereg);
        useremail = (EditText) findViewById(R.id.etUserEmailreg);
        userpassword = (EditText) findViewById(R.id.etUserPasswordreg);
        confirmpassword = (EditText) findViewById(R.id.etConfirmPasswordreg);

        regbutton = (Button) findViewById(R.id.btnRegister);
    }



    public void registeruser(View view) {

        String getname = username.getText().toString();
        String getemail = useremail.getText().toString();
        String getpassword = userpassword.getText().toString();
        String getconfirmpassword = confirmpassword.getText().toString();

        if (getname.isEmpty()) {
            Toast.makeText(this, "The username is Empty", Toast.LENGTH_SHORT).show();
        } else if (getemail.isEmpty()) {
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
        } else if (getpassword.isEmpty()) {
            Toast.makeText(this, "password is empty", Toast.LENGTH_SHORT).show();
        } else if (getconfirmpassword.isEmpty()) {
            Toast.makeText(this, "confirm password is empty", Toast.LENGTH_SHORT).show();
        } else if (!(getemail.isEmpty() && getpassword.isEmpty())){

            mAuth.createUserWithEmailAndPassword(getemail, getpassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, UserActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration not successful", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }


    }

    public void MovetoLogin (View view){
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }
}