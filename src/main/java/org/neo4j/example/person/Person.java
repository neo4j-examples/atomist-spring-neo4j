package org.neo4j.example.person;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Person {
    private Long id;
    private String name;
    private int born;
    @Relationship(type="KNOWS",direction = Relationship.UNDIRECTED)
    private Set<Person> friends = new HashSet<>();

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public Set<Person> getFriends() {
        return friends;
    }
    public void addFriend(Person friend) {
        friends.add(friend);
    }
}
