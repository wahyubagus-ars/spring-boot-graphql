package com.graphql.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "authors")
@Data
@Builder
@AllArgsConstructor
public class Author {
    private UUID id;
    private String name;
    private int age;
    @Column("book_id")
    private UUID bookId;
}
