package com.example.enroll_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Student {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            updatable = false,
            name = "sid"
    )
    private int Id;
    @NotNull
    @Size(min = 1)
    @Column(
            nullable = false
    )
    private String name;
    @NotNull
    @Column(
            nullable = false,
            unique = true
    )
    private String email;
    private String phone;
    private String dob;
    @JsonIgnoreProperties("studentList")
    @ManyToMany(mappedBy = "studentList")
    private List<Course> enroll;

    public Student() {
    }

    public List<Course> getEnroll() {
        return enroll;
    }

    public void setEnroll(List<Course> enroll) {
        this.enroll = enroll;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                '}';
    }

    public int getSid() {
        return Id;
    }

    public void setSid(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
