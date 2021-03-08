package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnregister;
    EditText etMail;
    EditText etPw;
    EditText etFn;
    EditText etLn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnregister = findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
        etFn=findViewById(R.id.fn);
        etLn=findViewById(R.id.ln);
        etMail=findViewById(R.id.email);
        etPw=findViewById(R.id.pw);


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register()
    {
        String email=etMail.getText().toString().trim();
        String password=etPw.getText().toString().trim();
        String firstname=etFn.getText().toString().trim();
        String lastname=etLn.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etMail.setError("Email required");
            etMail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etPw.setError("Password required");
            etPw.requestFocus();
            return;
        }

        if(firstname.isEmpty()){
            etFn.setError("Firstname required");
            etFn.requestFocus();
            return;
        }

        if(lastname.isEmpty()){
            etLn.setError("Lastname required");
            etLn.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(firstname, lastname, email);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity3.this, "Successfully registered", Toast.LENGTH_LONG).show();

                                FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(firstname).build();
                                muser.updateProfile(profileUpdates);
                            }
                            else{
                                Toast.makeText(MainActivity3.this, "Failed", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(MainActivity3.this, MainActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity3.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}