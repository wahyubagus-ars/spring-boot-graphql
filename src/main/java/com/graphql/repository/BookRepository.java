package com.graphql.repository;

import com.graphql.domain.dao.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class BookRepository {

    @Autowired
    private R2dbcEntityTemplate databaseClient;

    public Mono<Book> getBook(UUID id){
        return databaseClient.select(Book.class).matching(Query.query(CriteriaDefinition.from(Criteria.where("id").is(id)))).one();
    }

    public Flux<Book> getBooks() {
        return databaseClient.select(Book.class).all();
    }

    public Mono<UUID> createBook(Book book) {
        UUID bookId = UUID.randomUUID();
        book.setId(bookId);
        return databaseClient.insert(Book.class).using(book).thenReturn(bookId);
    }
}
