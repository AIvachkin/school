package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyService {

    ResponseEntity<Faculty> createFaculty (Faculty faculty);
    ResponseEntity<Faculty> getFacultyById (Long id);
    ResponseEntity<Faculty> updateFaculty (Faculty faculty);
    ResponseEntity<Faculty> removeFaculty (Long id);
    List<Faculty> getFacultiesByColor (String color);
    Collection<Faculty> getAll ();
    List<Faculty> findFacultyByColorOrName (String str);
//    ResponseEntity<String> getFacultyNameWithMaxLength ();


}
