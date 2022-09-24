package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.interfaceForSQL.AverageAgeStudents;
import ru.hogwarts.school.interfaceForSQL.CountAllStudents;
import ru.hogwarts.school.interfaceForSQL.CountStudentsByFaculties;
import ru.hogwarts.school.interfaceForSQL.LastFiveStudents;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.List;

@RestController
public class StudentsByCategoryController {

    private final StudentServiceImpl studentService;

    public StudentsByCategoryController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students-by-faculties")
    public List<CountStudentsByFaculties> getStudentsByCategories(){
        return studentService.getStudentsByCategory();
    }

    @GetMapping("/all-students-by-school")
    public List<CountAllStudents> getAllStudentsBySchool(){
        return studentService.getAllStudentsBySchool();
    }

    @GetMapping("/all-students-average-age")
    public List<AverageAgeStudents> getAverageAgeStudents(){
        return studentService.getAverageAgeStudents();
    }

    @GetMapping("/last-five-students")
    public List<LastFiveStudents> getLastFiveStudents(){
        return studentService.getLastFiveStudents();
    }

}
