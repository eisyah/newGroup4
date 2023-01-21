package com.example.newgroup4.model;

public class Student {
    //Student variables
    private int studID;
    private String studName;
    private String course;
    private int profilePic;


    /* getters and setters */
    public int getStudID() {
        return studID;
    }

    public void setStudID(int studID) {
        this.studID = studID;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }
    ////////////////////////////////////////////


    //to string method
    @Override
    public String toString() {
        return "Student{" +
                "studID=" + studID +
                ", studName='" + studName + '\'' +
                ", course='" + course + '\'' +
                ", profilePic=" + profilePic +
                '}';
    }
}
