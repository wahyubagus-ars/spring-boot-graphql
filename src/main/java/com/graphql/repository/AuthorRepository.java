package com.graphql.repository;

import com.graphql.domain.dao.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class AuthorRepository {

    @Autowired
    private R2dbcEntityTemplate databaseClient;

    public Mono<UUID> createAuthor(Author author) {
        UUID authorId = UUID.randomUUID();
        author.setId(authorId);
        return databaseClient.insert(Author.class).using(author).then().thenReturn(authorId);
    }

}
