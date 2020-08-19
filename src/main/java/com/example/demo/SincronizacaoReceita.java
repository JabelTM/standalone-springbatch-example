package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SincronizacaoReceita {

    private static Logger LOGGER = LoggerFactory.getLogger(SincronizacaoReceita.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SincronizacaoReceita.class, args);
    }

}