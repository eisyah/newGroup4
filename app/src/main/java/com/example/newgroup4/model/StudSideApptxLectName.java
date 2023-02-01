package com.example.newgroup4.model;

//pojo class for retrieving joined tables, appointment x lecturers
//this is to get the lecturer name for the appointment
//this is also most probably would only be used one time

public class StudSideApptxLectName {
    public String lectID;
    public String lectName;
    public String lectEmail;
    public String lectPhoneno;
    public String apptID;
    public String studID;
    public String date;
    public String time;
    public String reason;
    public String status;


    //getter and setters
    public String getLectID() {
        return lectID;
    }

    public void setLectID(String lectID) {
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

    public String getApptID() {
        return apptID;
    }

    public void setApptID(String apptID) {
        this.apptID = apptID;
    }

    public String getStudID() {
        return studID;
    }

    public void setStudID(String studID) {
        this.studID = studID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    ///////////////////////


    public StudSideApptxLectName(String lectID, String lectName, String lectEmail, String lectPhoneno, String apptID, String studID, String date, String time, String reason, String status) {
        this.lectID = lectID;
        this.lectName = lectName;
        this.lectEmail = lectEmail;
        this.lectPhoneno = lectPhoneno;
        this.apptID = apptID;
        this.studID = studID;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.status = status;
    }
}

