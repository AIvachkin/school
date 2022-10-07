package ru.hogwarts.school.service;


import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;

public interface StudentService {

    Student addStudent(Student student);

    Student findStudent(long id);

    Student editStudent(long id, Student student);

    void deleteStudent(long id);

    Collection<Student> findByAge(int age);

//    Avatar findAvanar(long studentId);

//    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Collection<Student> getAllStudents();

    List<String> getAllStudentsByLetter(Character letter);

    OptionalDouble getAverageAgeAllStudents();

    void getNameStudentInConsole ();
    void getNameStudentInConsoleSynchr();

//    Integer getCountOfAllStudents();
//
//    Double getAverageAgeOfStudents();
//
//    Collection<Student> getLastFiveStudents();

}
