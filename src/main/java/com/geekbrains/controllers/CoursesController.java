package com.geekbrains.controllers;

import com.geekbrains.entities.Course;
import com.geekbrains.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/courses")
@Transactional
public class CoursesController {
    private CoursesService coursesService;

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @RequestMapping("/list")
    @Transactional
    public String showCoursesList(Model model) {
        List<Course> allCourses = coursesService.getAllCoursesList();
        model.addAttribute("cousesList", allCourses);
        System.out.println(allCourses);
        return "courses-list";
    }

    @RequestMapping(path="/add", method=RequestMethod.GET)
    public String showAddForm(Model model) {
        Course course = new Course();
        course.setTitle("Unknown");
        model.addAttribute("course", course);
        return "add-course-form";
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    public String showAddForm(Course course) {
        coursesService.addCourse(course);
        return "redirect:/courses/list";
    }

    @RequestMapping(path="/remove/{id}", method=RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long courseId) {
        coursesService.removeById(courseId);
        return "redirect:/courses/list";
    }

//    @RequestMapping(path="/add", method=RequestMethod.POST)
//    public String showAddForm(@RequestParam(value="student") Student student) {
//        studentsService.addStudent(student);
//        return "redirect:/students/list";
//    }
//
//    @RequestMapping("/processForm")
//    public String processForm(@ModelAttribute("student") Student student) {
//        System.out.println(student.getFirstName() + " " + student.getLastName());
//        return "student-form-result";
//    }
//
//    // http://localhost:8189/students/showStudentById?id=5
//    @RequestMapping(path="/showStudentById", method=RequestMethod.GET)
//    public String showStudentById(Model model, @RequestParam int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        model.addAttribute("student", student);
//        return "student-form-result";
//    }
//
//    @RequestMapping(path="/getStudentById", method=RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentById(@RequestParam int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
//
//    @RequestMapping(path="/getStudentById/{sid}", method=RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentByIdFromPath(@PathVariable("sid") int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
}
