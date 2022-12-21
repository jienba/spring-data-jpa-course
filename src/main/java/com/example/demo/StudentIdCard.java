package com.example.demo;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity(name = "StudentIdCard")
@Table(
        name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_id_card_number_unique",
                        columnNames = "card_number"
                )
        }

)
public class StudentIdCard {

    @Id
    @SequenceGenerator(
            name = "student_id_card_generator",
            sequenceName = "student_id_card_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_id_card_generator"
    )
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Long id;

    @Column(
            name = "card_number",
            nullable = false,
            length = 15
    )
    private String cardNumber;

    @OneToOne(
            cascade = CascadeType.ALL
//            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id"
    )
    private Student student;

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard() {
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }
}
