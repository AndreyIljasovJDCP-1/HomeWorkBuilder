package org.example.people;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Тест класса Person.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonTest {
    private Person person;

    @Test
    @Order(1)
    @DisplayName("Тест: hasAge() return true, если установлен корректный возраст.")
    void hasAgeReturnTrueIfAgeRightSetUp() {
        person = new Person("Иван", "Иванов", 30);
        Assertions.assertTrue(person.hasAge());
    }

    @Test
    @Order(2)
    @DisplayName("Тест: hasAge() return false, если возраст не задан (=-1).")
    void hasAgeReturnFalseIfAgeNotSetUp() {
        person = new Person("Иван", "Иванов");
        Assertions.assertFalse(person.hasAge());
    }

    @Test
    @Order(3)
    @DisplayName("Тест: hasAddress() return true, если адрес установлен.")
    void hasAddressReturnTrue() {
        person = new Person("Иван", "Иванов", 30, "Москва");
        Assertions.assertTrue(person.hasAddress());
    }

    @Test
    @Order(4)
    @DisplayName("Тест:  hasAddress() return false, если адрес не установлен (= null).")
    void hasAddressReturnFalse() {
        person = new Person("Иван", "Иванов", 30);
        Assertions.assertFalse(person.hasAddress());
    }

    @Order(5)
    @DisplayName("Тест: setAge() установка корректного возраста.")
    @ParameterizedTest(name = "Возраст: {0}")
    @ValueSource(ints = {0, 1, 124, 100})
    void setAgeReturnAge(int age) {
        person = new Person("Иван", "Иванов");
        Assertions.assertEquals(age, person.setAge(age));
    }

    @Order(6)
    @DisplayName("Тест: setAge() throws IllegalArgumentException,")
    @ParameterizedTest(name = "если возраст: {0}")
    @ValueSource(ints = {-1, 125, -125, 200})
    void setAgeThrowExceptionIfAgeWrong(int age) {
        person = new Person("Иван", "Иванов");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> person.setAge(age));
    }

    @Order(7)
    @DisplayName("Тест: Конструктор throws IllegalArgumentException, ")
    @ParameterizedTest(name = "если возраст: {0}")
    @ValueSource(ints = {-1, 125, -125, 200})
    void constructorThrowExceptionIfAgeWrong(int age) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Person("Иван", "Иванов", age));
    }

    @Test
    @Order(8)
    @DisplayName("Тест: happyBirthday() увеличить возраст на 1, если возраст установлен.")
    void happyBirthdayPersonHasAgeReturnIncreasedAge() {
        person = new Person("Иван", "Иванов", 30);
        person.happyBirthday();
        Assertions.assertEquals(31, person.getAge());
    }

    @Test
    @Order(9)
    @DisplayName("Тест: happyBirthday() не изменять возраст, если возраст не установлен (=-1).")
    void happyBirthdayUnknownAgeDoNothing() {
        person = new Person("Иван", "Иванов");
        person.happyBirthday();
        Assertions.assertEquals(-1, person.getAge());
    }

    @Test
    @Order(10)
    @DisplayName("Тест: newChildBuilder() return PersonBuilder с родительскими полями \"surname\" и \"address\".")
    void newChildBuilderReturnPersonBuilderWithParentsFieldsSurnameAddress() {
        person = new Person("Иван", "Иванов", 30, "Москва");
        PersonBuilder personBuilder = person.newChildBuilder(10);
        Assertions.assertAll("PersonBuilder with parent fields Surname and Address",
                () -> Assertions.assertEquals("Иванов", personBuilder.getSurname()),
                () -> Assertions.assertEquals("Москва", personBuilder.getAddress())
        );
    }
}