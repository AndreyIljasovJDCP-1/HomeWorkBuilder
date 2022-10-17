package org.example;

import org.example.people.Person;
import org.example.people.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        try {
            Person mom = new PersonBuilder()
                    .name("Анна")
                    .surname("Вольф")
                    .age(30)
                    .address("Сидней")
                    .build();
            Person son = mom.newChildBuilder(0)
                    .name("Антошка")
                    .build();
            System.out.println("У " + mom + " есть сын, \n" + son);

            son.happyBirthday();
            mom.happyBirthday();
            mom.setAddress("Moscow");
            son.setAddress("Moscow");

            System.out.println("У " + mom + " есть сын, \n" + son);

            Person father = new Person("Stas", "Velichko");
            father.happyBirthday();
            System.out.println(father);

            father.setAge(45);
            father.setAddress("Rome");

            Person son2 = father.newChildBuilder(5)
                    .name("Ivan")
                    .build();

            son2.happyBirthday();
            father.happyBirthday();

            System.out.println("У " + father + " есть сын, \n" + son2);

        } catch (IllegalStateException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}