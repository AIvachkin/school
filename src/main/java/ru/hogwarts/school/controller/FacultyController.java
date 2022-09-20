package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping()
    public Collection<Faculty> getFacultyByColor(@RequestParam String color) {
        return facultyService.findFacultyByColor(color);
    }

    @GetMapping("/colorOrName")
    public List<Faculty> findByColorOrNameIgnoreCase(@RequestParam String colorOrName) {
        return facultyService.findFacultyByColorOrName(colorOrName);
    }

//    @GetMapping("/{id}/student")
//    public Faculty getFacultyByStudent(@PathVariable Long id) {
//        return facultyService.findFacultyByStudent(id);
//    }

    @GetMapping("/{id}/student")
    public List<Student> getStudentsByFaculty(@PathVariable Long id) {
        return facultyService.findStudentsByFaculty(id);
    }

//    @GetMapping("/idStudent")
//    public Faculty findFacultyByIdStudent (@RequestParam int idStudent) {
//        return facultyService.findFacultyByIdStudent(idStudent);
//
//    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(id, faculty);
        if (foundFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        Faculty deletedFaculty = facultyService.deleteFaculty(id);
        if (deletedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedFaculty);
    }
}
