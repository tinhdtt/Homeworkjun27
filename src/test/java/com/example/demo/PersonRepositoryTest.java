package com.example.demo;

import model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repository.PersonRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
class PersonRepositoryTest {
   // @Autowired
    //private PersonRepository people;

    public class Person {
        String name;
        String nationality;
        int age;

        public Person(String name, String nationality, int age) {
            this.name = name;
            this.nationality = nationality;
            this.age = age;
        }
    }

    ArrayList<Person> people = new ArrayList<>(List.of(
            new Person("Vinh", "Vietnam", 28),
            new Person("Lan", "Vietnam", 24),
            new Person("John", "USA", 27),
            new Person("Lee", "China", 67),
            new Person("Kim", "Korea", 22),
            new Person("Long", "Vietnam", 18),
            new Person("Jungho", "Korea", 19),
            new Person("Tian", "China", 20),
            new Person("Clara", "USA", 40),
            new Person("Mikura", "Japan", 27),
            new Person("Sony", "Japan", 29),
            new Person("Xiang", "China", 78),
            new Person("Peter", "France", 18),
            new Person("Haloy", "Malaysia", 20),
            new Person("Magie", "Malaysia", 32)
    ));

    @Test
    @DisplayName("----Bài 1.1: Đếm số người theo từng quốc tịch----")
    void baitap1_1() {
        Map<String, Integer> nationalityMap = new HashMap<>();
        for (Person person : people) {
            Integer count = nationalityMap.get(person.nationality);
            if (count == null) {
                count = 1;
            } else {
                count = count + 1;
            }
            nationalityMap.put(person.nationality, count);
        }
        for (Map.Entry<String, Integer> entry : nationalityMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @Test
    @DisplayName("----Bài 1.2: Sắp xếp theo tên những người trên 25 tuổi----")
    void baitap1_2() {
        List<Person> people25 = new ArrayList<>();
        for (Person person : people) {
            if (person.age > 25) {
                people25.add(person);
            }
        }
        people25.sort(Comparator.comparing(p -> p.name));
        for (Person person : people25) {
            System.out.println(person.name + " - " + person.nationality + " - age: " + person.age);
        }
    }

    @Test
    @DisplayName("----Bài 1.3: Tính trung bình tuổi của người theo từng quốc gia----")
    void baitap1_3() {
        Map<String, List<Integer>> nationalityMap = new HashMap<>();
        for (Person person : people) {
            List<Integer> ages = nationalityMap.get(person.nationality);
            if (ages == null) {
                ages = new ArrayList<>();
                ages.add(person.age);
            } else {
                ages.add(person.age);
            }
            nationalityMap.put(person.nationality, ages);
        }
        for (Map.Entry<String, List<Integer>> entry : nationalityMap.entrySet()) {
            float avgAge = 0;
            for (Integer age : entry.getValue()) {
                avgAge += age;
            }
            System.out.printf("%s: %.1f%n", entry.getKey(), avgAge / entry.getValue().size());
        }
    }

    @Test
    @DisplayName("----Bài 1.4: Đánh giá tuổi mỗi người----")
    void baitap1_4() {
        List<Person> people25 = new ArrayList<>();
        for (Person person : people) {
            if (person.age < 20) {
                System.out.println(person.name + " - " + person.nationality + " - " + person.age + " : " +"Nổi loạn");
            } else if (person.age>=20 && person.age <=30){
                System.out.println(person.name + " - " + person.nationality + " - " + person.age + " : " + "Việc làm");
            } else if (person.age>=31 && person.age <=40){
                System.out.println(person.name + " - " + person.nationality + " - " + person.age + " : " + "Sự nghiệp");
            } else if (person.age >= 41){
                System.out.println(person.name + " - " + person.nationality + " - " + person.age + " : " + "Hưởng thụ");
            }
        }
    }
}

