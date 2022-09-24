package ru.hogwarts.school.service;

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

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudent(long id, Student student) {
        Optional <Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student fromDB = optional.get();
            fromDB.setName(student.getName());
            fromDB.setAge(student.getAge());
            return studentRepository.save(fromDB);
        }
        return null;
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public List<Student> findStudentByAgeBetween(int ageMin, int ageMax){
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

//    public Faculty findFacultyByStudent (int idStudent){
//        return studentRepository.findByFaculty_Id(idStudent);
//    }

    public Faculty findFacultyByStudent (Long studentId){
        return studentRepository.findStudentById(studentId).getFaculty();
    }

    public List<CountStudentsByFaculties> getStudentsByCategory (){
        return studentRepository.getStudentsByCategory();
    }

    public List<CountAllStudents> getAllStudentsBySchool (){
        return studentRepository.getAllStudentsBySchool();
    }

    public List<AverageAgeStudents> getAverageAgeStudents (){
        return studentRepository.getAverageAgeStudents();
    }

    public List<LastFiveStudents> getLastFiveStudents (){
        return studentRepository.getLastFiveStudents();
    }
}

