package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.interfaceForSQL.AverageAgeStudents;
import ru.hogwarts.school.interfaceForSQL.CountAllStudents;
import ru.hogwarts.school.interfaceForSQL.LastFiveStudents;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.interfaceForSQL.CountStudentsByFaculties;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
//    private final AvatarRepository avatarRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
//        this.avatarRepository = avatarRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public Student addStudent(Student student) {
        logger.info("Was invoked method for add student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method to find student by id = {}", id);
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudent(long id, Student student) {
        logger.info("Was invoked method to edit student by id = {}", id);
        Optional <Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student fromDB = optional.get();
            fromDB.setName(student.getName());
            fromDB.setAge(student.getAge());
            return studentRepository.save(fromDB);
        }
        logger.error("There is no student with id = {}", id);
        return null;
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method to delete student by id = {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public List<Student> findByAge(int age) {
        logger.info("Was invoked method to find student by age = {}", age);
        return studentRepository.findAllByAge(age);
    }

    public List<Student> findStudentByAgeBetween(int ageMin, int ageMax){
        logger.info("Was invoked method to find student by age between {} and {} ", ageMin, ageMax);
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

//    public Faculty findFacultyByStudent (int idStudent){
//        return studentRepository.findByFaculty_Id(idStudent);
//    }

    public Faculty findFacultyByStudent (Long studentId){
        logger.info("Was invoked method to find faculty by student id = {}", studentId);
        return studentRepository.findStudentById(studentId).getFaculty();
    }

    public List<CountStudentsByFaculties> getStudentsByCategory (){
        logger.info("Was invoked method for get all students by category");
        return studentRepository.getStudentsByCategory();
    }

    public List<CountAllStudents> getAllStudentsBySchool (){
        logger.info("Was invoked method for get all students by school");
        return studentRepository.getAllStudentsBySchool();
    }

    public List<AverageAgeStudents> getAverageAgeStudents (){
        logger.info("Was invoked method for get average age students");
        return studentRepository.getAverageAgeStudents();
    }

    public List<LastFiveStudents> getLastFiveStudents (){
        logger.info("Was invoked method for get last five students");
        return studentRepository.getLastFiveStudents();
    }
}

