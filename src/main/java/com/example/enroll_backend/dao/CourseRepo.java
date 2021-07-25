package com.example.enroll_backend.dao;

import com.example.enroll_backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
