package com.studynest.edtech.service;

import com.studynest.edtech.model.courseModel;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class courseService {

    // Simulating a course retrieval by ID
    public courseModel getCourseById(Long courseId) {
        // You would typically retrieve the course from the database here.
        // For now, let's simulate with a dummy course
        courseModel course = new courseModel();
        course.setId(courseId);
        course.setName("Sample Course");
        course.setDescription("This is a sample course description.");
        course.setPrice(5000.0);
        return course;
    }

    public List<courseModel> getCoursesByUserId(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoursesByUserId'");
    }
}
