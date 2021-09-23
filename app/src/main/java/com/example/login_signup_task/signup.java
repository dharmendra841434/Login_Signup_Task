package com.example.login_signup_task;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity implements View.OnFocusChangeListener {

    static EditText usrname,mobile,email,pass,rpass;
     static TextView login;
    static Button signupbtn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference refrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usrname = (EditText) findViewById(R.id.username);
        usrname.setOnFocusChangeListener(this);
        mobile = (EditText) findViewById(R.id.mymobile);
        mobile.setOnFocusChangeListener(this);
        email = (EditText) findViewById(R.id.myemail);
        email.setOnFocusChangeListener(this);
        pass =(EditText) findViewById(R.id.mypass);
        pass.setOnFocusChangeListener(this);
        rpass =(EditText) findViewById(R.id.remypass);
        rpass.setOnFocusChangeListener(this);
        signupbtn =(Button) findViewById(R.id.signup);
        login = (TextView) findViewById(R.id.loginlink);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this,MainActivity.class));
                finish();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myuname=usrname.getText().toString();
                String mymob=mobile.getText().toString();
                String myemail=email.getText().toString();
                String mypass=pass.getText().toString();
                String myrepass=rpass.getText().toString();

                if (!myuname.isEmpty())
                {
                    usrname.setError(null);
                    usrname.setEnabled(true);
                    if (!mymob.isEmpty())
                    {
                        mobile.setError(null);
                        mobile.setEnabled(true);

                        if (!myemail.isEmpty())
                        {
                            email.setError(null);
                            email.setEnabled(true);

                            if (!mypass.isEmpty())
                            {
                                pass.setError(null);
                                pass.setEnabled(true);

                                if (!myrepass.isEmpty())
                                {
                                    rpass.setError(null);
                                    rpass.setEnabled(true);

                                    SendData();


                                }
                                else
                                {
                                    rpass.setError("Re-Enter Password");
                                }
                            }
                            else
                            {
                                pass.setError("Enter Password");
                            }
                        }
                        else
                        {
                            email.setError("Enter Email");
                        }
                    }
                    else
                    {
                        mobile.setError("Enter Mobile Number");
                    }
                }
                else
                {
                    usrname.setError("Enter Username");
                }
            }
        });


    }

    private void SendData() {


                    firebaseDatabase =firebaseDatabase.getInstance();
                    refrence = firebaseDatabase.getReference("Mydatabase");


                    String uname = usrname.getText().toString();
                    String mob = mobile.getText().toString();
                    String em = email.getText().toString();
                    String pass1 = pass.getText().toString();
                    storedata dt = new storedata(uname,mob,em,pass1);
                    refrence.child(uname).setValue(dt);

                    AlertDialog alertDialog = new AlertDialog.Builder(signup.this)
                            .setMessage("Registration Sucessfull")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(signup.this,MainActivity.class));
                                    finish();
                                }
                            }).show();
                }

    @Override
    public void onFocusChange(View view, boolean b) {
        String validpass= pass.getText().toString();
        String validrepass= rpass.getText().toString();


        switch (view.getId())
        {
            case R.id.username:
                formvalidation.isValidUsername();
                break;
            case R.id.mymobile:
                formvalidation.isValidmobile();
                break;
            case R.id.myemail:
               formvalidation.isValidemail();
                break;
            case R.id.mypass:
                formvalidation.isValidpassword();
                break;
            case R.id.remypass:
               formvalidation.isValidrepassword();
                if (validpass.equals(validrepass))
                {
                   rpass.setError(null);
                   rpass.setEnabled(true);
                }
                else
                {
                    rpass.setError("Password does not match");
                }

                break;


        }





    }
}