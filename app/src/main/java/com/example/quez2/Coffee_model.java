package com.example.quez2;

public class Coffee_model {

    String Date, write;

    public Coffee_model(){}
    public Coffee_model(String Date, String write) {
        this.Date = Date;
        this.write = write;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getwrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }}
