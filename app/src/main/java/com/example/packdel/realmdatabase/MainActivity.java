package com.example.packdel.realmdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import Model.Person;
import Realm.MyRealm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    public static void log(String log){
        Log.i("AMP", log);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRealm myRealm = new MyRealm(getApplicationContext());

        //myRealm.Create_Person(11892, "amir", 20, "nora", "mandy", "jupoo");

        //myRealm.Create_Person(34501, "hamid", 22, "lira");

        //myRealm.Create_Person(45663, "ehsan", 20, "semandra");

        //log(String.valueOf(myRealm.Read_Person("amir").count()));

        RealmResults<Person> persons = myRealm.Read_Person("amir");

        for(Person p : persons){

            log(p.getName());
        }

    }
}
