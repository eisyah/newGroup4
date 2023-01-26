package com.example.newgroup4.model;

public class Appointment {
    private int apptID;
    private String studID;
    private String lectID;
    private String date;
    private String time;
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
