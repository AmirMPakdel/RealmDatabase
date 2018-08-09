package Realm;

import android.content.Context;

import java.util.Arrays;

import Model.Person;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class MyRealm {

    private Realm realm;

    public MyRealm(Context context){

        Realm.init(context);

        realm = Realm.getDefaultInstance();
    }

    public void Create_Person(int id, String name, int age, String ... dogs){

        realm.beginTransaction();

        Person user = realm.createObject(Person.class, id); // Create a new object

        user.setName(name);

        user.setAge(age);

        RealmList<String> realmList = new RealmList<>();

        // foreach arg in args add arg into the list
        realmList.addAll(Arrays.asList(dogs));

        user.setDogs(realmList);

        realm.commitTransaction();

    }

    public RealmResults<Person> Read_Person(String ... names){


        RealmResults<Person> result = realm.where(Person.class)
                //.beginGroup()
                .equalTo("name", names[0])
                //.or()
                //.contains("name", "Jo")
                //.endGroup()
                .findAll();

        return result;
    }

    public void Update_Person(final String name, final String new_name){

        // Asynchronously update objects on a background thread
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                Person user = bgRealm.where(Person.class).equalTo("name", name).findFirst();

                user.setName(new_name);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Original queries and Realm objects are automatically updated.
                //puppies.size(); // => 0 because there are no more puppies younger than 2 years old
                //managedDog.getAge();   // => 3 the dogs age is updated
            }
        });
    }

    public void Delete_Person(String name){

        RealmResults<Person> result = realm.where(Person.class).equalTo("name", name).findAll();
        result.deleteAllFromRealm();
        // or result.clear()
    }

}
