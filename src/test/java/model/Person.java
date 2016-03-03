package model;

import java.util.Date;

/**
 * Created by ChenYanping on 15-2-2.
 */
public class Person {
    private String name;
    private Date birth;

    public Person(){}
    public Person(String name, Date birth){
        this.name=name;
        this.birth=birth;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
