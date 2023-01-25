package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.  run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository){
        return args -> {
            var faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@techsuki.sn",firstName,lastName);
            Student student = new Student(firstName, lastName, email, faker.number().numberBetween(17, 45));
            student.addBook(new Book("Think and Grow Rich", LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("The Richest Man of Babylone", LocalDateTime.now().minusYears(4)));
            student.addBook(new Book("The Alchemist", LocalDateTime.now()));


            StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
            student.setStudentIdCard(studentIdCard);

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 1L),
                    student,
                    new Course("Cryptographic", "IT"),
                    LocalDateTime.now()
            ));
            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 2L),
                    student,
                    new Course("UI/UX", "IT"),
                    LocalDateTime.now()
            ));

            studentRepository.save(student);
            studentRepository.findById(1L)
                    .ifPresent(student1 -> {
                        System.out.println("Lazy Loading 😴😴😴");
                        List<Book> books = student.getBooks();
                        books.forEach(book -> {
                            System.out.println(student1.getFirstName()+ " borrowed "+ book.getBookName() );
                        });
                    });
//
//            studentIdCardRepository.findById(1L)
//                    .ifPresent(System.out::println);

//            studentRepository.deleteById(1L);
        };
    }

    private void generateRandomStudent(StudentRepository studentRepository) {
        var faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@techsuki.sn",firstName,lastName);
            Student student = new Student(firstName, lastName, email, faker.number().numberBetween(17, 45));
            studentRepository.save(student);
        }
    }



}
