package com.graphql.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class GraphqlRequestDto {
    private String query;
    private String operationName;
    private Map<String, Object> variables;
}
