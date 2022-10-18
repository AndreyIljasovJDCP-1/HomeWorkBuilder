package org.example.people;

public class Person {
    private final String name;
    private final String surname;
    private int age;
    private String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.age = -1;
    }

    public Person(String name, String surname, int age) {
        this(name, surname);
        this.age = setAge(age);
    }

    public Person(String name, String surname, int age, String address) {
        this(name, surname, age);
        this.address = address;
    }

    public PersonBuilder newChildBuilder(int age) {
        return new PersonBuilder().surname(surname).age(age).address(address);
    }

    boolean hasAge() {
        return age > -1;
    }

    public void happyBirthday() {
        if (hasAge()) {
            age++;
        } else {
            System.out.println("Возраст не установлен =>(-1)");
        }
    }

    boolean hasAddress() {
        return address != null;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int setAge(int age) {
        if (age > -1 && age < 125) {
            return this.age = age;
        } else {
            throw new IllegalArgumentException("Недопустимый возраст: " + age);
        }
    }


    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nSurname: %s\nAge: %d\nAddress: %s\n",
                name, surname, age, address);
    }
}
