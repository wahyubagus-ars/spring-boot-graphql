package com.graphql;

import com.graphql.service.BookService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.io.IOException;

@SpringBootApplication
@Configuration
public class SpringGraphqlApplication {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(SpringGraphqlApplication.class, args);
	}

	@Bean
	public GraphQL graphQL() throws IOException {
		SchemaParser schemaParser = new SchemaParser();
		ClassPathResource schema = new ClassPathResource("schema.graphql");
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema.getInputStream());

		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBook", bookService.getBook()))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBooks", bookService.getBooks()))
				.type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("createBook", bookService.createBook()))
				.build();

		SchemaGenerator generator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = generator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		return GraphQL.newGraphQL(graphQLSchema).build();
	}
}
