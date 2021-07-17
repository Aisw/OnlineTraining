package com.example.train.service;

import java.lang.reflect.Method;

public class A {

    public  void method(Father obj) {
        obj.method();
        System.out.println(obj.getClass());
    }

    public static void main(String[] args) throws Exception {
        Child child = new Child();
        System.out.println(child.i);
        Father f = child;
        f.method();
        System.out.println(f.i);
//        f.method();
//        Method getI = Child.class.getDeclaredMethod("getI");
//        Object invoke = getI.invoke(f, null);
//        System.out.println(invoke);
    }
}

class Father {
    int i = 10;
    void method() {
        System.out.println("f");
    }
}

class Child extends Father{
    int i = 1;
    void method() {
        System.out.println(i);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}