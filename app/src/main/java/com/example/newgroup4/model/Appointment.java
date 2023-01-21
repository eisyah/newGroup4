package com.example.newgroup4.model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int apptID;
    private String studID;
    private String lectID;
    private Date date;
    private Time time;
    private String reason;
    private String status;

    /* getters and setters */

    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public String getStudID() {
        return studID;
    }

    public void setStudID(String studID) {
        this.studID = studID;
    }

    public String getLectID() {
        return lectID;
    }

    public void setLectID(String lectID) {
        this.lectID = lectID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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
    //////////////////////////////////

    //to string method
    @Override
    public String toString() {
        return "Appointment{" +
                "apptID=" + apptID +
                ", studID='" + studID + '\'' +
                ", lectID='" + lectID + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
