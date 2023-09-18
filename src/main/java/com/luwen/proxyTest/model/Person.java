package com.luwen.proxyTest.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Person implements Action{

    private String name;

    @Override
    public void action() {
        System.out.println("person action...");
    }
}
