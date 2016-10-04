package org.neo4j.example;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableNeo4jRepositories("org.neo4j.example.person")
public class GraphConfiguration extends Neo4jConfiguration {
    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("org.neo4j.example.person");
    }
}
