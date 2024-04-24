package com.example.ip_demo1;

public class EmailNamePair {

    private String email;
    private String prenume;

    private String date;

    private String last_msg;

    public EmailNamePair() {
    }

    public EmailNamePair(String email, String prenume) {
        this.email = email;
        this.prenume = prenume;
    }

    public EmailNamePair(String email, String prenume, String date, String last_msg) {
        this.email = email;
        this.prenume = prenume;
        this.date = date;
        this.last_msg = last_msg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLast_msg() {
        return last_msg;
    }

    public void setLast_msg(String last_msg) {
        this.last_msg = last_msg;
    }

    @Override
    public String toString() {
        return "EmailNamePair{" +
                "email='" + email + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }
}
