package com.example.my_proj;

public class Item {
    public Item(String name, String position) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Float getSalary() {
        return salary;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    String name;
    String position;
    Float salary;
}
