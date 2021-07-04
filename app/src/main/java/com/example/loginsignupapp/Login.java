package com.example.loginsignupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if (s1.equals("") || s2.equals(""))
                {
                    Toast.makeText(Login.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase sql = openOrCreateDatabase("RegistrationData", MODE_PRIVATE, null);
                    sql.execSQL("create table if not exists student (name varchar, email varchar, password varchar)");
                    String s4 = "select * from student where name= '"+s1+"' and email= '"+s2+"'";
                    Cursor ca = sql.rawQuery(s4, null);
                    if(ca.getCount()>0)
                    {
                        Toast.makeText(Login.this, "Login Complete", Toast.LENGTH_SHORT).show();
                        Intent k = new Intent(Login.this, Welcome.class);
                        startActivity(k);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Login.this, "You are not Registered! Please Sign Up first.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}