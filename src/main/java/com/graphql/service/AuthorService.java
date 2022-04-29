package com.graphql.service;

import com.graphql.domain.dao.Author;
import com.graphql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public Mono<String> createAuthor(String authorName, int age, UUID bookId) {
        Author author = Author.builder()
                .name(authorName)
                .age(age)
                .bookId(bookId)
                .build();

        return repository.createAuthor(author).map(Object::toString);
    }



}
