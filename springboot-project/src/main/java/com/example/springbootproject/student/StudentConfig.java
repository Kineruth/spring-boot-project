package com.example.springbootproject.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {


    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) throws IOException {

        return args -> {
            Student sarah = new Student(
                    "Sarah",
                    "sarah@gmail.com",
                    LocalDate.of(1994, MAY,5)
            );
            Student miri = new Student(
                    "Miri",
                    "miri@gmail.com",
                    LocalDate.of(1988, JANUARY,25)
            );
            Student jess = new Student(
                    "Jess",
                    "jess@gmail.com",
                    LocalDate.of(1995, MARCH,18)
            );

            Student ronaldo = new Student(
                    "Ronaldo",
                    "ronaldo@gmail.com",
                    LocalDate.of(1991, AUGUST,9)
            );
            Student max = new Student(
                    "Max",
                    "max@gmail.com",
                    LocalDate.of(1985, FEBRUARY,14)
            );

            Student jill = new Student(
                    "Jill",
                    "jill@gmail.com",
                    LocalDate.of(1990, DECEMBER,20)
            );

            Student bobbi = new Student(
                    "Bobbi",
                    "bobbi@gmail.com",
                    LocalDate.of(2000, JULY,13)
            );

            //save to DB - hibernate running:
            repository.saveAll(List.of(sarah, miri, jess, ronaldo, max, jill, bobbi));
        };
    }

}

