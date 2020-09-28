package com.codelabs.userinterface.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String phone;
    String area;
    String address;
    String city;
    String state;
    String zip;
    String email;
    String birthday;

    public User(String name, String phone, String area, String address, String city, String state, String zip, String email, String birthday) {
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.birthday = birthday;
    }

    @Ignore
    public User(int id, String name, String phone, String area, String address, String city, String state, String zip, String email, String birthday) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.birthday = birthday;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getArea() { return area; }

    public void setArea(String area) { this.area = area; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }

    public void setZip(String zip) { this.zip = zip; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getBirthday() { return birthday; }

    public void setBirthday(String birthday) { this.birthday = birthday; }
}

