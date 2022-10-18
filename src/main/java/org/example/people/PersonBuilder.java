package org.example.people;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class PersonBuilder {
    private String name;
    private String surname;
    private Integer age;
    private String address;

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder age(int age) {
        if (age < 0 || age > 124) {
            throw new IllegalArgumentException("Недопустимый возраст: " + age);
        } else {
            this.age = age;
            return this;
        }
    }

    public PersonBuilder address(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                var value = field.get(this);
                if (value == null) {
                    sj.add(field.getName() + " = null");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (name == null || surname == null || age == null || address == null) {
            throw new IllegalStateException("Не могу создать объект Person, не заполнены поля: " + sj);
        }

        return new Person(name, surname, age, address);
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
