package ru.mirea.dikanev.nikita.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.mirea.dikanev.nikita.messenger")
        public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
