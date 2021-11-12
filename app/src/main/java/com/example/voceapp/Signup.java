package com.example.voceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText username, email, password, confirmpassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //initialise variables
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.cpassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String emailu = email.getText().toString();
                String pass = password.getText().toString();
                String passc = confirmpassword.getText().toString();

                if (user.equals("")||emailu.equals("")||pass.equals("")||passc.equals(""))
                    Toast.makeText(Signup.this,"Please enter all credentials",Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(passc)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,emailu,pass);
                            if (insert==true){
                                Toast.makeText(Signup.this, "Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Homepage.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(Signup.this, "Registration failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            Toast.makeText(Signup.this, "User Already Exists", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(Signup.this, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Signin.class);
                startActivity(intent);
            }
        });
    }
}