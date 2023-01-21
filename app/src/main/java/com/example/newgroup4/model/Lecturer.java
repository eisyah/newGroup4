package com.example.newgroup4.model;

public class Lecturer {

    private int lectID;
    private String lectName;
    private String lectEmail;
    private String lectPhoneno;

    /* getters and setters */

    public int getLectID() {
        return lectID;
    }

    public void setLectID(int lectID) {
        this.lectID = lectID;
    }

    public String getLectName() {
        return lectName;
    }

    public void setLectName(String lectName) {
        this.lectName = lectName;
    }

    public String getLectEmail() {
        return lectEmail;
    }

    public void setLectEmail(String lectEmail) {
        this.lectEmail = lectEmail;
    }

    public String getLectPhoneno() {
        return lectPhoneno;
    }

    public void setLectPhoneno(String lectPhoneno) {
        this.lectPhoneno = lectPhoneno;
    }
    ///////////////////////////////

    //to string method
    @Override
    public String toString() {
        return "Lecturer{" +
                "lectID=" + lectID +
                ", lectName='" + lectName + '\'' +
                ", lectEmail='" + lectEmail + '\'' +
                ", lectPhoneno='" + lectPhoneno + '\'' +
                '}';
    }
}
