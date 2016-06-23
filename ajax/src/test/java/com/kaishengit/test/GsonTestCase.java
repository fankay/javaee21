package com.kaishengit.test;

import com.google.gson.Gson;
import com.kaishengit.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonTestCase {


    @Test
    public void testToObject() {
        User user = new User(1,"tom","Usa",87.9F);
        //Object -> {}
        Gson gson = new Gson();
        String json = gson.toJson(user);

        System.out.println(json);
    }

    @Test
    public void testMapToObject() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",12);
        map.put("message","今天记得给我打电话");

        // Map -> {}
        String json = new Gson().toJson(map);

        System.out.println(json);

    }

    @Test
    public void testArrayToObject() {

        String[] names = {"tom","jack","rose","张丽丽"};

        // array -> []
        String json = new Gson().toJson(names);
        System.out.println(json);
    }

    @Test
    public void testObjectArrayToJson() {
        User[] users = new User[3];
        users[0] = new User(1,"tom","usa",78.5F);
        users[1] = new User(25,"lily","中国",78.5F);
        users[2] = new User(25,"lily","中国",78.5F);

        //Object array -> []
        String json = new Gson().toJson(users);
        System.out.println(json);

    }

    @Test
    public void testListToJson() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"tom","usa",78.5F));
        userList.add(new User(25,"lily","中国",78.5F));
        userList.add(new User(25,"lily","中国",78.5F));

        // list -> []

        String json = new Gson().toJson(userList);
        System.out.println(json);

    }

    @Test
    public void testOtherToJson() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"tom","usa",78.5F));
        userList.add(new User(25,"lily","中国",78.5F));
        userList.add(new User(25,"lily","中国",78.5F));

        Map<String,Object> map = new HashMap<>();
        map.put("result","success");
        map.put("data",userList);

        //map -> {}
        String json = new Gson().toJson(map);

        System.out.println(json);


    }

}
