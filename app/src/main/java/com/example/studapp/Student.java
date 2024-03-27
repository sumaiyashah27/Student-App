package com.example.studapp;
public class Student {
    private int rollNumber;
    private String name;
    private String city;

    public Student(int rollNumber, String name, String city) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.city = city;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
