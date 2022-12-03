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
                    "Adama D.",
                    "BA",
                    "adamajb@techsuki.sn",25);
            Student jiraya = new Student(
                    "Almamy D.",
                    "Camara",
                    "jiraya@techsuki.sn",24);

            System.out.println("Adding jienba and jiraya");
            studentRepository.saveAll(List.of(jienba, jiraya));

            System.out.print("Number of students:");
            System.out.println(studentRepository.count());

            studentRepository
                    .findById(1L)
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with ID 1 not found!")
            );
            studentRepository
                    .findById(3L)
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with ID 3 not found!")
            );

            System.out.println("Select all students");
            List<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            System.out.println("Delete jiraya");
            studentRepository.deleteById(2L);

            System.out.print("Number of students:");
            System.out.println(studentRepository.count());
        };
    }

}
