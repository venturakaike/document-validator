package com.innovation.validator;

import com.innovation.validator.ws.configuration.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class,
                ModuleConfiguration.class,
                MongoConfiguration.class,
                JacksonConfiguration.class,
                SwaggerConfiguration.class
        ).run(args);
    }

}