package com.example.springbootproject.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

/*
The sequence maps class "Student" to a @Table in our DB
 */
@Entity
@Table (name = "student")
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    //sequence is recommended for postgreSQL
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    private String name;
    private String email;
    @Transient
    private Integer age;
    private LocalDate dob; //date of birth


    public Student() {

    }

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob) { //without id
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears(); //transits to current age
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                '}';
    }
}
