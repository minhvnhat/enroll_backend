package com.example.enroll_backend.controller;

import com.example.enroll_backend.model.Course;
import com.example.enroll_backend.model.form.EnrollForm;
import com.example.enroll_backend.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class EnrollController {

    private final EnrollService enrollService;

    @Autowired
    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @GetMapping("/courses/most_enroll")
    public Optional<Course> mostEnrolled() {
        return enrollService.getMostEnrolledCourse();
    }

    @PostMapping("/courses/{cid}/enroll")
    public void enrollCourse(
                               @RequestBody EnrollForm enrollForm,
                               @PathVariable int cid) {
        if (enrollForm.getOp().equals("enroll")) {
            enrollService.enrollCourse(cid, enrollForm.getSid());
        } else if (enrollForm.getOp().equals("unenroll")) {
            enrollService.unenrollCourse(cid, enrollForm.getSid());
        }
    }



}
