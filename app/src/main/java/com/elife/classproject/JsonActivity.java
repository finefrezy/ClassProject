package com.elife.classproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {
    private TextView mJsonStr;
    private TextView mJsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        mJsonStr = (TextView) findViewById(R.id.json_str);
        mJsonObject = (TextView) findViewById(R.id.json_object);

        People p = new People();
        p.people = new ArrayList<People.Person>();
        People.Person person = new People().new Person();
        person.firstName = "san";
        person.lastName = "zhang";
        person.email = "djs";
        p.people.add(person);
        People.Person person1 = new People().new Person();
        person1.firstName = "si";
        person1.lastName = "li";
        person1.email = "df";
        p.people.add(person1);

        List<People> pList = new ArrayList<People>();
        pList.add(p);
        pList.add(p);
        Gson gson = new Gson();

        String json = gson.toJson(pList);
        mJsonStr.setText(json);

//        People p1 = gson.fromJson(json, People.class);
//
//        mJsonObject.setText(p1.people.size() + "");

        Type listType = new TypeToken<List<People>>() {}.getType();

        List<People> listP = gson.fromJson(json, listType);
        mJsonObject.setText(listP.size() + "");

    }
}
