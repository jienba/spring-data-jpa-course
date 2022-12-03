package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student jienba = new Student(
                    "Adama",
                    "BA",
                    "adamajb@techsuki.sn",25);
            Student jienba2 = new Student(
                    "Adama",
                    "BA",
                    "adamajb2@techsuki.sn",45);
            Student jiraya = new Student(
                    "Almamy D.",
                    "Camara",
                    "jiraya@techsuki.sn",24);

            studentRepository.saveAll(List.of(jienba, jiraya, jienba2));

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeGreaterThanEqual("Adama", 25)
                    .forEach(System.out::println);

            /*studentRepository
                    .findStudentsByFirstNameEqualsAndAgeGreaterThanEqualNative("Adama", 25)
                    .forEach(System.out::println);*/

            System.out.println("Deleting Adama 3");
            System.out.println(studentRepository.deleteStudentById(3L));



        };
    }

}
