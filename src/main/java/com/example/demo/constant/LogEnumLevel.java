package com.example.demo.constant;


public enum LogEnumLevel {


    DEBUG(0), INFO(1), ERROR(2);

    LogEnumLevel(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }


}
