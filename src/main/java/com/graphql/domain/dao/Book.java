package com.graphql.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "books")
@Data
@Builder
@AllArgsConstructor
public class Book {

    @Id
    private UUID id;
    private String name;
    private int pages;
}
