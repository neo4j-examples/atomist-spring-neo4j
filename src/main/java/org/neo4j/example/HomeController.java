package org.neo4j.example;

import org.neo4j.example.person.Person;
import org.neo4j.example.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(path = "/")
    public String home() {
        return "<h1>People Service</h1>" +
                "<ul>" +
                "<li><a href='/people'>List People</a></li>" +
                "<li><form method='post' action='/people'><input name='name'/><input name='born'/><input type='submit' value='Add Person'></form></li>" +
                "<li><form method='post' action='/friend'><input name='person'/><input name='friend'/><input type='submit' value='Add Friend'/></form></li>" +
                "</ul>";
    }

    @RequestMapping(path = "/people")
    public Iterable<Person> people() {
        return personRepository.findAll(0);
    }

    @RequestMapping(path = "/people", method = RequestMethod.POST)
    public Person addPerson(String name, int born) {
        Person person = new Person();
        person.setName(name);
        person.setBorn(born);
        return personRepository.save(person);
    }
    @RequestMapping(path = "/friend", method = RequestMethod.POST)
    public Person addPerson(String person, String friend) {
        Person p1 = personRepository.findByName(person);
        Person p2 = personRepository.findByName(friend);
        p1.addFriend(p2);
        return personRepository.save(p1);
    }
}
