package ru.hogwarts.school.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;


    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public ResponseEntity<Faculty> createFaculty(Faculty faculty) {
        logger.info("Was invoked method to create faculty");
        if (faculty == null) {
            logger.error("Can't create faculty, when faculty is null");
            return ResponseEntity.badRequest().build();
        }
        logger.debug("Was created {}", faculty);
        return ResponseEntity.ok(facultyRepository.save(faculty));
    }


    public ResponseEntity<Faculty> getFacultyById(Long id) {
        logger.info("Was invoked method to find faculty by id = {}", id);
        Optional<Faculty> byId = facultyRepository.findById(id);
        if (byId.isEmpty()) {
            logger.error("There is no faculty with id = {}", id);
            return ResponseEntity.notFound().build();
        }
        logger.debug("Faculty was founded {}", id);
        return ResponseEntity.ok(byId.get());
    }


    public ResponseEntity<Faculty> updateFaculty(Faculty faculty) {
        logger.info("Was invoked method to update faculty");
        if (faculty == null) {
            logger.error("Can't update faculty, when faculty is null");
            return ResponseEntity.badRequest().build();
        }
        if (facultyRepository.findById(faculty.getId()).isEmpty()) {
            logger.error("There is no faculty your id");
            return ResponseEntity.notFound().build();
        }
        logger.debug("{} update", faculty);
        return ResponseEntity.ok(facultyRepository.save(faculty));


//            Optional<Faculty> optional = facultyRepository.findById(id);
//            if (optional.isPresent()) {
//                Faculty fromDb = optional.get();
//                fromDb.setName(faculty.getName());
//                fromDb.setColor(faculty.getColor());
//                return facultyRepository.save(fromDb);
//            } else {
//                return null;
//            }

    }

    public ResponseEntity<Faculty> removeFaculty(Long id) {
        logger.info("Was invoked method to remove faculty");
        facultyRepository.deleteFacultyById(id);
        logger.debug("Faculty was deleted by id={}", id);
        return ResponseEntity.ok().build();
    }

    public Collection<Faculty> getAll() {
//        return faculties.values();
        logger.info("Was invoked method to get all faculty");
        return facultyRepository.findAll();
    }

    public List<Faculty> getFacultiesByColor(String color) {
        logger.info("Was invoked method to find faculty by color = {}", color);
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findFacultyByColorOrName(String colorOrName) {
        logger.info("Was invoked method to find faculty by color or name: {}", colorOrName);
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName);
    }

    public Collection<Student> findStudentsByFaculty(Long id) {
        logger.info("Was invoked method to find students by faculty id = {}", id);
        return facultyRepository.findFacultyById(id).getStudent();
    }


//    public Faculty findFacultyByIdStudent (int idStudent){
//        return facultyRepository.findByStudent(idStudent);
//    }
}
