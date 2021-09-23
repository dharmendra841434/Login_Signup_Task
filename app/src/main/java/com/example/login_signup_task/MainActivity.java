package com.example.login_signup_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText uname,pass;
    Button loginbtn;
    TextView signup;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = (EditText) findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signuplink);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,signup.class));

       }
      });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String muname=uname.getText().toString();
                String mpass=pass.getText().toString();

                if (!muname.isEmpty())
                {

                    uname.setError(null);
                    uname.setEnabled(true);

                    if (!mpass.isEmpty())
                    {
                        pass.setError(null);
                        pass.setEnabled(true);

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference reference = firebaseDatabase.getReference("Mydatabase");
                        Query checkusername = reference.orderByChild("username").equalTo(muname);

                        checkusername.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists())
                                {
                                    uname.setEnabled(true);
                                    String checkpass = snapshot.child(muname).child("password").getValue(String.class);
                                    if (checkpass.equals(mpass))
                                    {
                                        pass.setEnabled(true);
                                        mProgressDialog = ProgressDialog.show(MainActivity.this,"Login Process", "Please wait...",false,false);
                                        Toast.makeText(getApplicationContext(),"Login sucessfull",Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(MainActivity.this,home.class));
                                        finish();

                                    }
                                    else
                                    {
                                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                                .setMessage("Wrong Password")
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        pass.setText(null);

                                                    }
                                                }).show();
                                        pass.requestFocus();
                                    }
                                }
                                else
                                {
                                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                            .setMessage("Wrong Username")
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    uname.setText(null);

                                                }
                                            }).show();
                                    uname.requestFocus();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    else
                    {
                        pass.setError("Enter Password");

                    }

                }
                else
                {
                    uname.setError("Enter Username");
                }
            }
        });
            }
    }