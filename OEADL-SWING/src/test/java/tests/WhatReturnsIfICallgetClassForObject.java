package tests;
public class WhatReturnsIfICallgetClassForObject {
    public static void main(String[] args) {
        String s = "sda";
        Object o = (Object)s;
        System.out.println(o.getClass());
    }
}
