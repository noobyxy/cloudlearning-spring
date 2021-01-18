package com.yxy.cl.entity.dto.form;

public class ModifyPassWordForm {
    private String userName;

    private String currentPassWord;

    private String newPassWord;

    private String confirmPassWord;

    public ModifyPassWordForm(String userName, String currentPassWord, String newPassWord, String confirmPassWord) {
        this.userName = userName;
        this.currentPassWord = currentPassWord;
        this.newPassWord = newPassWord;
        this.confirmPassWord = confirmPassWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurrentPassWord() {
        return currentPassWord;
    }

    public void setCurrentPassWord(String currentPassWord) {
        this.currentPassWord = currentPassWord;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
    }
}
