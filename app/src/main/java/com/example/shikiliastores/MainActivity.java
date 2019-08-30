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

public class MainActivity extends AppCompatActivity {
    EditText mEmailaddress,mPassword;
    Button mButtonLogin;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailaddress=(EditText)findViewById(R.id.etUserEmaillg);
        mPassword=(EditText)findViewById(R.id.etUserPasswordlg);
        mButtonLogin=(Button)findViewById(R.id.btnlogin);

        mAuth=FirebaseAuth.getInstance();
    }

    public void loginuser(View view) {
        String getmEmail=mEmailaddress.getText().toString();
        String getmPass=mPassword.getText().toString();

        if (getmEmail.isEmpty()){
            Toast.makeText(this, "Email value is empty", Toast.LENGTH_SHORT).show();
        } else if (getmPass.isEmpty()){
            Toast.makeText(this, "password Empty", Toast.LENGTH_SHORT).show();
    } else if(!(getmEmail.isEmpty() && getmPass.isEmpty())){

            mAuth.signInWithEmailAndPassword(getmEmail,getmPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,UserActivity.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }



    public void Movetoregistration(View view) {
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }

    public void movetouser(View view) {
        startActivity(new Intent(MainActivity.this,UserActivity.class));
    }

    public void movetohome(View view) {
        startActivity(new Intent(MainActivity.this,Main2Activity.class));
    }

    public void facility(View view) {
        startActivity(new Intent(MainActivity.this,FacilityActivity.class));
    }
}
