package com.kaishengit.test;

public class Son extends Father<String,Integer> {

    public Son() {
        System.out.println("Son: " + this);
    }
}
