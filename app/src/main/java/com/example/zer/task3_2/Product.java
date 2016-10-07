package com.example.zer.task3_2;

public class Product {

    private String name;
    private Integer count;

    public Product(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {

        return name;
    }

    public Integer getCount() {
        return count;
    }
}
