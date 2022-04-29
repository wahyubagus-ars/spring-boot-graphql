package com.graphql.controller;

import com.graphql.domain.dto.GraphqlRequestDto;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class GraphqlController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> execute(@RequestBody GraphqlRequestDto request){

        return Mono.fromCompletionStage(graphQL.executeAsync(ExecutionInput.newExecutionInput()
                .query(request.getQuery())
                .operationName(request.getOperationName())
                .variables(request.getVariables())
                .build())
        ).map(ExecutionResult::toSpecification);
    }

}
