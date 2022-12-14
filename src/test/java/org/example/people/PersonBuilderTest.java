package org.example.people;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Тест класса PersonBuilder.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonBuilderTest {

    @Order(1)
    @DisplayName("Тест: age() добавить корректный возраст в PersonBuilder.")
    @ParameterizedTest(name = "Возраст: {0}")
    @ValueSource(ints = {0, 1, 124, 100})
    void ageAddAgeToPersonBuilderIfAgeRight(int age) {
        PersonBuilder personBuilder = new PersonBuilder().age(age);
        Assertions.assertEquals(age, personBuilder.getAge());
    }

    @Order(2)
    @DisplayName("Тест: age() throws IllegalArgumentException, ")
    @ParameterizedTest(name = "если возраст некорректный: {0}")
    @ValueSource(ints = {-1, 125, -125, 200})
    void ageThrowExceptionIfAgeWrong(int age) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PersonBuilder().age(age));
    }

    @Test
    @Order(3)
    @DisplayName("Тест: build() создание объекта Person.")
    void buildReturnNewPerson() {
        Person person = new PersonBuilder()
                .name("Иван")
                .surname("Иванов")
                .age(30)
                .address("Москва")
                .build();

        Assertions.assertAll("Проверка Person",
                () -> Assertions.assertEquals("Иван", person.getName()),
                () -> Assertions.assertEquals("Иванов", person.getSurname()),
                () -> Assertions.assertEquals(30, person.getAge()),
                () -> Assertions.assertEquals("Москва", person.getAddress())
        );
    }

    @Test
    @Order(4)
    @DisplayName("Тест: build() throws IllegalStateException, если заданы не все поля.")
    void buildThrowIllegalStateExceptionIfNotAllFields() {

        Assertions.assertAll("Throw IllegalStateException",
                () -> Assertions.assertThrows(IllegalStateException.class, () -> new PersonBuilder()
                        .build()),
                () -> Assertions.assertThrows(IllegalStateException.class, () -> new PersonBuilder()
                        .name("Иван")
                        .build()),
                () -> Assertions.assertThrows(IllegalStateException.class, () -> new PersonBuilder()
                        .name("Иван")
                        .surname("Иванов")
                        .build()),
                () -> Assertions.assertThrows(IllegalStateException.class, () -> new PersonBuilder()
                        .name("Иван")
                        .surname("Иванов")
                        .age(30)
                        .build())
        );
    }
}