package com.example.enroll_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Course {
    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            updatable = false,
            name = "cid"
    )
    private int Id;
    @NotNull
    @Size(min = 1)
    @Column(
            nullable = false
    )
    private String name;
    @Column(columnDefinition = "varchar(255) default 'TBA'")
    private String startDate;
    @Column(columnDefinition = "varchar(255) default 'TBA'")
    private String endDate;

    @JsonIgnoreProperties("enroll")
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Student> studentList;

    public int getCid() {
        return Id;
    }

    public void setCid(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + Id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
