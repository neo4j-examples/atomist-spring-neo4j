package org.neo4j.example.person;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface PersonRepository extends GraphRepository<Person> {
    Person findByName(String person);
}
