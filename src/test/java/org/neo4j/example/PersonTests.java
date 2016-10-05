package org.neo4j.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.example.person.Person;
import org.neo4j.example.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GraphConfiguration.class)
public class PersonTests {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testCreatePerson() {
        Person person = new Person();
        person.setName("John");
        person.setBorn(1990);
        Person saved = personRepository.save(person);
        Person loaded = personRepository.findOne(saved.getId());
        assertEquals(person.getName(),loaded.getName());
        assertEquals(person.getBorn(),loaded.getBorn());
    }
    @Test
    public void testFindByName() {
        Person person = new Person();
        person.setName("Jane");
        Person saved = personRepository.save(person);
        Person loaded = personRepository.findByName(person.getName());
        assertEquals(person.getName(),loaded.getName());
        assertEquals(saved.getId(),loaded.getId());
    }

    @Test
    public void testAddFriend() {
        Person john = new Person();
        john.setName("John");
        Person jane = new Person();
        jane.setName("Jane");
        jane.addFriend(john);
        Person saved = personRepository.save(jane);
        Person loaded = personRepository.findByName(john.getName());
        assertEquals(john.getName(),loaded.getName());
        Person jane2 = personRepository.findOne(saved.getId());
        assertEquals(saved.getId(),jane2.getId());
        assertEquals(saved.getFriends().iterator().next().getName(),jane2.getFriends().iterator().next().getName());
    }
}
