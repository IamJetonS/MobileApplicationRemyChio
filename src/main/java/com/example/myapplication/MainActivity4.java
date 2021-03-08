package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    EditText etNAME, etIBAN, etCURR, etAMO;
    Button btnAdd;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        myDb=new DatabaseHelper(this);

        etAMO=findViewById(R.id.EditTextAccountAmount);
        etCURR=findViewById(R.id.EditTextAccountCurrency);
        etIBAN=findViewById(R.id.EditTextAccountIban);
        etNAME=findViewById(R.id.EditTextAccountName);
        btnAdd=findViewById(R.id.buttonAddAcc);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });
    }

    public void AddData()
    {
        boolean isInserted = myDb.insertData(etNAME.getText().toString(), etAMO.getText().toString(), etIBAN.getText().toString(), etCURR.getText().toString());
        if(isInserted==true) {
            Toast.makeText(MainActivity4.this, "Data inserted", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity4.this, MainActivity2.class));
        }
        else
        {
            Toast.makeText(MainActivity4.this, "Data not inserted", Toast.LENGTH_LONG).show();
        }
    }


}