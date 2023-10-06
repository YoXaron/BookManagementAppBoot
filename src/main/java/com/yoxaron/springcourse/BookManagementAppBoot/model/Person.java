package com.yoxaron.springcourse.BookManagementAppBoot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message="Name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String fullName;

    @Column(name = "birth_year")
    @Min(value = 1900, message = "Age should be greater than 1900")
    @Max(value = 2015, message = "Age should be less than 2015")
    private int birthYear;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }
}
