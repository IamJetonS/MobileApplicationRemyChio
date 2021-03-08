package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {


    DatabaseHelper myDb;
    TextView tvID;
    TextView tvName;
    Button btnAddAcc, btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myDb=new DatabaseHelper(this);

        tvID=findViewById(R.id.TextViewID);
        tvName=findViewById(R.id.TextViewName);
        btnAddAcc=findViewById(R.id.buttonAddAcc);
        btnViewAll=findViewById(R.id.buttonViewAll);

        btnAddAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAcc();
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAll();
            }
        });


        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        tvID.setText("ID "+ currentFirebaseUser.getUid());
        tvName.setText("Name "+ currentFirebaseUser.getDisplayName());

    }
    public void AddAcc()
    {
        startActivity(new Intent(MainActivity2.this, MainActivity4.class));
    }

    public void viewAll(){
        Cursor res = myDb.getAllData();
        if(res.getCount()==0){
            showMessage("Error", "Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Name : "+res.getString(1)+"\n");
            buffer.append("Amount : "+res.getString(2)+"\n");
            buffer.append("Iban : "+res.getString(3)+"\n");
            buffer.append("Currency : "+res.getString(4)+"\n\n");
        }

        showMessage("Data", buffer.toString());

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}