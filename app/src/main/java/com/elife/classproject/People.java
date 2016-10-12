package com.elife.classproject;

import java.util.List;

/**
 * Created by tzhang on 2016/9/9.
 *
 * {
 "people":[
 {"firstName":"Brett","lastName":"McLaughlin","email":"aaaa"},
 {"firstName":"Jason","lastName":"Hunter","email":"bbbb"},
 {"firstName":"Elliotte","lastName":"Harold","email":"cccc"}
 ]
 }

 */
public class People {

    public List<Person> people;


    public class  Person {
        public String firstName;
        public String lastName;
        public  String email;
    }

}

