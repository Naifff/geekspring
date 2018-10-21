package com.geekbrains.services;

import com.geekbrains.entities.Course;
import com.geekbrains.repositories.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CoursesService {
    private CoursesRepository coursesRepository;

    @Autowired
    public void setCoursesRepository(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public CoursesService() {
    }

    @Transactional
    public List<Course> getAllCoursesList() {
        return (List<Course>) coursesRepository.findAll();
    }

    public void addCourse(Course c) {
        coursesRepository.save(c);
    }

    public void removeById(Long id) {
        coursesRepository.deleteById(id);
    }
}
