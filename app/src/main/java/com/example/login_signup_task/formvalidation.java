package com.example.login_signup_task;

import android.widget.EditText;

import java.util.regex.Pattern;

public class formvalidation extends signup{


    public static void isValidUsername()
    {
        String validuname = usrname.getText().toString();
        if (!Pattern.matches("^[A-Za-z]\\w{0,29}$", validuname)) {
            usrname.setError("use Alphabet & Numbers");
        }
        else {
            usrname.setError(null);
            usrname.setEnabled(true);
        }
    }
    public static void isValidmobile()
    {
        String validmobile = mobile.getText().toString();
        if (!Pattern.matches("(0/91)?[7-9][0-9]{0,9}", validmobile)) {
            mobile.setError("Enater valid Number");
        }
        else {
            mobile.setError(null);
            mobile.setEnabled(true);
        }
    }
    public static void isValidemail()
    {
        String validemail = email.getText().toString();
        if (!Pattern.matches("^[a-zA-Z0-9]{0,20}+[@]{1}+[a-zA-Z0-9]{0,20}+[.]{1}+[a-zA-Z0-9]{0,20}$", validemail)) {
            email.setError("Enter Email id");
        }
        else {
            email.setError(null);
            email.setEnabled(true);
        }
    }
    public static void isValidpassword()
    {
        String validpass = pass.getText().toString();
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", validpass)) {
            pass.setError("use Uppercase,lowercase,numbers & Symball");
        }
        else {
            pass.setError(null);
            pass.setEnabled(true);
        }
    }
    public static void isValidrepassword()
    {
        String validreps = rpass.getText().toString();
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", validreps)) {
            rpass.setError("use Uppercase,lowercase,numbers & Symball");
        }
        else {
            rpass.setError(null);
            rpass.setEnabled(true);
        }
    }

}
