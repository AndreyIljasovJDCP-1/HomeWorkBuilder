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
    @DisplayName("True, если установлен корректный возраст.")
    void hasAgeReturnTrueIfAgeRightSetUp() {
        person = new Person("Иван", "Иванов", 30);
        Assertions.assertTrue(person.hasAge());
    }

    @Test
    @Order(2)
    @DisplayName("False, если возраст не задан (=-1).")
    void hasAgeReturnFalseIfAgeNotSetUp() {
        person = new Person("Иван", "Иванов");
        Assertions.assertFalse(person.hasAge());
    }

    @Order(3)
    @ParameterizedTest(name = "Установить корректный возраст {0}")
    @ValueSource(ints = {0, 1, 124, 100})
    void setAgeReturnAge(int age) {
        person = new Person("Иван", "Иванов");
        Assertions.assertEquals(age, person.setAge(age));
    }

    @Order(4)
    @ParameterizedTest(name = "Throw IllegalArgumentException, для недопустимого возраста {0}")
    @ValueSource(ints = {-1, 125, -125, 200})
    void setAgeThrowExceptionIfAgeWrong(int age) {
        person = new Person("Иван", "Иванов");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> person.setAge(age));
    }

    @Order(5)
    @ParameterizedTest(name = "Throw IllegalArgumentException, для недопустимого возраста {0} в конструкторе")
    @ValueSource(ints = {-1, 125, -125, 200})
    void constructorThrowExceptionIfAgeWrong(int age) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Person("Иван", "Иванов", age));
    }

    @Test
    @Order(6)
    @DisplayName("Увеличить возраст на 1, если установлен корректный возраст.")
    void happyBirthdayPersonHasAgeReturnIncreasedAge() {
        person = new Person("Иван", "Иванов", 30);
        person.happyBirthday();
        Assertions.assertEquals(31, person.getAge());
    }

    @Test
    @Order(7)
    @DisplayName("Не изменять возраст, если возраст не установлен (=-1).")
    void happyBirthdayUnknownAgeDoNothing() {
        person = new Person("Иван", "Иванов");
        person.happyBirthday();
        Assertions.assertEquals(-1, person.getAge());
    }

    @Test
    @Order(8)
    @DisplayName("True, если задан адрес.")
    void hasAddressReturnTrue() {
        person = new Person("Иван", "Иванов", 30, "Москва");
        Assertions.assertTrue(person.hasAddress());
    }

    @Test
    @Order(9)
    @DisplayName("False, если адрес не установлен (= null).")
    void hasAddressReturnFalse() {
        person = new Person("Иван", "Иванов", 30);
        Assertions.assertFalse(person.hasAddress());
    }

    @Test
    @Order(10)
    @DisplayName("Вернуть PersonBuilder с родительскими полями \"surname\" и \"address\".")
    void newChildBuilderReturnPersonBuilderWithParentsFieldsSurnameAddress() {
        person = new Person("Иван", "Иванов", 30, "Москва");
        PersonBuilder personBuilder = person.newChildBuilder(10);
        Assertions.assertAll("PersonBuilder with parent fields Surname and Address",
                () -> Assertions.assertEquals("Иванов", personBuilder.getSurname()),
                () -> Assertions.assertEquals("Москва", personBuilder.getAddress())
        );
    }
}