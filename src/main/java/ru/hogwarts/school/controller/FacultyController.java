package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyServiceImpl;

    public FacultyController(FacultyServiceImpl facultyServiceImpl) {
        this.facultyServiceImpl = facultyServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return facultyServiceImpl.createFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        if (facultyServiceImpl.getFacultyById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return facultyServiceImpl.getFacultyById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyServiceImpl.getAll());
    }

    @GetMapping()
    public Collection<Faculty> getFacultyByColor(@RequestParam String color) {
        return facultyServiceImpl.getFacultiesByColor(color);
    }

    @GetMapping("/colorOrName")
    public List<Faculty> findByColorOrNameIgnoreCase(@RequestParam String colorOrName) {
        return facultyServiceImpl.findFacultyByColorOrName(colorOrName);
    }

//    @GetMapping("/{id}/student")
//    public Faculty getFacultyByStudent(@PathVariable Long id) {
//        return facultyService.findFacultyByStudent(id);
//    }

    @GetMapping("/{id}/student")
    public Collection<Student> getStudentsByFaculty(@PathVariable Long id) {
        return facultyServiceImpl.findStudentsByFaculty(id);
    }

//    @GetMapping("/idStudent")
//    public Faculty findFacultyByIdStudent (@RequestParam int idStudent) {
//        return facultyService.findFacultyByIdStudent(idStudent);
//
//    }


    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        if (facultyServiceImpl.updateFaculty(faculty) == null) {
            return ResponseEntity.badRequest().build();
        }
        return facultyServiceImpl.updateFaculty(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        if (facultyServiceImpl.removeFaculty(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return facultyServiceImpl.removeFaculty(id);
    }
}
