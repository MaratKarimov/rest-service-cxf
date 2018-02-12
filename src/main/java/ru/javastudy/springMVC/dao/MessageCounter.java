package ru.javastudy.springMVC.dao;

import java.util.concurrent.atomic.AtomicInteger;

public class MessageCounter {
    private static AtomicInteger id = new AtomicInteger();

    public static Integer getNextValue() {
        return id.incrementAndGet();
    }

    public static Integer getCurrValue() {
        return id.get();
    }
}
