package com.example.ip_demo1;

public class EmailNamePair {

    private String email;
    private String prenume;

    public EmailNamePair() {
    }

    public EmailNamePair(String email, String prenume) {
        this.email = email;
        this.prenume = prenume;
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

    @Override
    public String toString() {
        return "EmailNamePair{" +
                "email='" + email + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }
}
