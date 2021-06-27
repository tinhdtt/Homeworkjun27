package repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {
    private List<Person> people = new ArrayList<Person>();

    public PersonRepository() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file;
        try {
            file = ResourceUtils.getFile("classpath:static/person.json.json");
            people.addAll(mapper.readValue(file, new TypeReference<List<Person>>() {
            }));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAll() {
        return people;
    }

    public Map<String, Long> groupPeopleByNationalThenCount() {
        return people
                .stream()
                .collect(Collectors.groupingBy(Person::getNationality, Collectors.counting()));
    }

    public void sortPeopleGreaterThan25() {
        List<Person> people25 = new ArrayList<>();
        for (Person person : people) {
            if (person.age > 25) {
                people25.add(person);
            }
        }
        people25.sort(Comparator.comparing(p -> p.name));
        people25.sort(Comparator.comparing(p -> p.name));
        for (Person person : people25) {
            System.out.println(person.name + " - " + person.nationality + " - age: " + person.age);

        }
    }
}