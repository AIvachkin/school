package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

//    public FacultyService(FacultyRepository facultyRepository) {
//        this.facultyRepository = facultyRepository;
//    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(long id, Faculty faculty) {
//        if (faculties.containsKey(faculty.getId())) {
//            faculties.put(faculty.getId(), faculty);
//        if (faculties.containsKey(id)) {
//            faculties.put(id, faculty);
//            return faculty;
//        }
//        return null;

        Optional<Faculty> optional = facultyRepository.findById(id);
        if (optional.isPresent()) {
            Faculty fromDb = optional.get();
            fromDb.setName(faculty.getName());
            fromDb.setColor(faculty.getColor());
            return facultyRepository.save(fromDb);
        } else {
            return null;
        }
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculty() {
//        return faculties.values();
        return facultyRepository.findAll();
    }

    public List<Faculty> findFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findFacultyByColorOrName(String colorOrName) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName);
    }

    public Faculty findFacultyByStudent(Long id) {
        return studentRepository.findStudentById(id).getFaculty();
    }


//    public Faculty findFacultyByIdStudent (int idStudent){
//        return facultyRepository.findByStudent(idStudent);
//    }
}
