package com.yxy.cl.entity.dto.form;

public class UserRegisterForm {
    private String username;

    private String password;

    private String confirmpassword;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRegisterForm(String username, String password, String confirmpassword) {
        this.username = username;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public UserRegisterForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
