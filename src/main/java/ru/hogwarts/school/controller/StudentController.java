package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> getStudentByAge(@RequestParam(required = false) Integer age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findStudentByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/ageBetween")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam Integer ageMin,
                                                       @RequestParam Integer ageMax) {
        return studentService.findStudentByAgeBetween(ageMin, ageMax);
    }

//    @GetMapping("/{id}/faculty")
//    public List<Student> getStudentsByFaculty(@PathVariable Long id) {
//        return studentService.findStudentsByFaculty(id);
//    }

    @GetMapping("/{id}/faculty")
    public Faculty getFacultyByStudent(@PathVariable Long id) {
        return studentService.findFacultyByStudent(id);
    }

//    Единый запрос по возрасту и id
//    @GetMapping()
//    public ResponseEntity getStudentByAgeOrById(@RequestParam(required = false) int age,
//                                                @RequestParam(required = false) Long id) {
//        if (age > 0) {
//            List<Student> foundStudentByAge = studentService.findStudentByAge(age);
//            if (!foundStudentByAge.isEmpty()) {
//                return ResponseEntity.ok(foundStudentByAge);
//            }
//        }
//
//        Student foundStudentById = studentService.findStudent(id);
//        if (foundStudentById == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(foundStudentById);
//
//
//    }

//    @PostMapping
//    public Student createStudent(@RequestBody Student student) {
//        return studentService.createStudent(student);
//    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (student.getId() !=  0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be empty!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody Student student) {
        Student foundStudent = studentService.editStudent(id, student);
        if (foundStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
//    public Student deleteStudent(@PathVariable Long id) {
//        return studentService.deleteStudent(id);
//    }
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
