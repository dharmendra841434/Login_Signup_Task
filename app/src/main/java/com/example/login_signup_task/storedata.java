package com.example.login_signup_task;

public class storedata {

    String  username,mobile,email,password;

    public storedata() {

    }

    public storedata(String username, String mobile, String email, String password) {
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
