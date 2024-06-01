package com.example.ip_demo1.model;

import java.util.Map;

public class User
{
    public User() {}

    public User(Integer id, String firstName, String lastName, String cnp, String age, String street, String city, String county, String country, String phoneNumber, String emailAddress, String profession, String workplace, String password, AccessType accessType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.age = age;
        this.street = street;
        this.city = city;
        this.county = county;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.profession = profession;
        this.workplace = workplace;
        this.password = password;
        this.accessType = accessType;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public AccessType getAccessType()
    {
        return accessType;
    }

    public String getAge()
    {
        return age;
    }

    public String getCity()
    {
        return city;
    }

    public String getCnp()
    {
        return cnp;
    }

    public String getCountry()
    {
        return country;
    }

    public String getCounty()
    {
        return county;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getProfession()
    {
        return profession;
    }

    public String getWorkplace()
    {
        return workplace;
    }

    public String getStreet()
    {
        return street;
    }

    public void setAccessType(AccessType accessType)
    {
        this.accessType = accessType;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setCnp(String cnp)
    {
        this.cnp = cnp;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public void setCounty(String county)
    {
        this.county = county;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setWorkplace(String workplace)
    {
        this.workplace = workplace;
    }

    private Integer id;
    private String firstName;
    private String lastName;
    private String cnp;
    private String age;
    private String street;
    private String city;
    private String county;
    private String country;
    private String phoneNumber;
    private String emailAddress;
    private String profession;
    private String workplace;
    private String password;
    private AccessType accessType;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cnp='" + cnp + '\'' +
                ", age=" + age +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", profession='" + profession + '\'' +
                ", workplace='" + workplace + '\'' +
                ", password='" + password + '\'' +
                ", accessType=" + accessType +
                '}';
    }
}

