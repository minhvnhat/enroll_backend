package com.example.enroll_backend.service;

import com.example.enroll_backend.dao.CourseRepo;
import com.example.enroll_backend.dao.StudentRepo;
import com.example.enroll_backend.model.Course;
import com.example.enroll_backend.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollService {
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Autowired
    public EnrollService(CourseRepo courseRepo, StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public List<Course> getCourses() {
        return courseRepo.findAll();
    }

    public Optional<Course> getMostEnrolledCourse() {
        List<Course> courseList = courseRepo.findAll();
        if (courseList.isEmpty()) {
            return Optional.empty();
        }
        else {
            Course mostEnrolled = courseList.get(0);
            int maxCount = mostEnrolled.getStudentList().size();
            for (Course i: courseList) {
                int newCount = i.getStudentList().size();
                if (newCount > maxCount) {
                    maxCount = newCount;
                    mostEnrolled = i;
                }
            }
            if (maxCount == 0) {
                return Optional.empty();
            }
            return Optional.of(mostEnrolled);
        }
    }

    @Transactional
    public void enrollCourse(int cid, int sid) {
        Course course = courseRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException(
                "course with id " + cid + " does not exist."
        ));
        Student student = studentRepo.findById(sid).orElseThrow(() -> new ResourceNotFoundException(
                "student with id " + sid+ " does not exist."
        ));
        for (Student i: course.getStudentList()) {
            if (i.getSid() == sid) {
                throw new IllegalStateException(
                        "student with id " + sid+ " has already enrolled.");
            }
        }
        course.getStudentList().add(student);
    }

    @Transactional
    public void unenrollCourse(int cid, int sid) {
        Course course = courseRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException(
                "course with id " + cid + " does not exist."
        ));
        Student student = studentRepo.findById(sid).orElseThrow(() -> new ResourceNotFoundException(
                "student with id " + sid+ " does not exist."
        ));
        if (!course.getStudentList().contains(student)) {
            throw new IllegalStateException(
                    "student with id " + sid+ " has not enrolled.");
        }
        course.getStudentList().remove(student);
    }
}
