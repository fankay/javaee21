package com.kaishengit.test;

import org.junit.Test;

import java.util.UUID;

public class MyTestCase {

    @Test
    public void testUUID() {

        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();

        System.out.println(key);
    }

}
