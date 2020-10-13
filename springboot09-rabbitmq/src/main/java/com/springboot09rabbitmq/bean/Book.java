package com.springboot09rabbitmq.bean;

public class Book {
    private String name;
    private String auth;

    public Book() {
    }

    public Book(String name, String auth) {
        this.name = name;
        this.auth = auth;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", auth='" + auth + '\'' +
                '}';
    }
}
