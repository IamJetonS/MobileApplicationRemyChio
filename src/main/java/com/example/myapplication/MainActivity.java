package com.example.myapplication;

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
    Button btn;
    Button btnregister;
    EditText etEmail, etPw;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        etEmail=findViewById(R.id.EditTextEmail);
        etPw=findViewById(R.id.EditTextPassword);
        btnregister=findViewById(R.id.buttonregister);
        mAuth=FirebaseAuth.getInstance();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_in();
            }
        });

    }

    public void register()
    {
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
    }

    public void sign_in()
    {
        Toast.makeText(MainActivity.this, "u there", Toast.LENGTH_LONG).show();
        String email=etEmail.getText().toString().trim();
        String pw=etPw.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Rock", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}