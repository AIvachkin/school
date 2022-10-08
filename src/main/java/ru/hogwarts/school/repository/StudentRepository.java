package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.interfaceForSQL.AverageAgeStudents;
import ru.hogwarts.school.interfaceForSQL.CountAllStudents;
import ru.hogwarts.school.interfaceForSQL.CountStudentsByFaculties;
import ru.hogwarts.school.interfaceForSQL.LastFiveStudents;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(int age);

    List<Student> findByAgeBetween(int ageMin, int ageMax);

    List<Student> findByFaculty_Id(Long facultyId);

    Student findStudentById(Long id);

    @Query(value = "select faculty_id, count(*)\n" +
            "from student\n" +
            "group by faculty_id", nativeQuery = true)
    List<CountStudentsByFaculties> getStudentsByCategory();

    @Query(value = "select count(*)\n" +
            "from student", nativeQuery = true)
    List<CountAllStudents> getAllStudentsBySchool();

    @Query(value = "select avg(age)\n" +
            "from student", nativeQuery = true)
    List<AverageAgeStudents> getAverageAgeStudents();

    @Query(value = "select *\n" +
            "from student\n" +
            "order by id desc limit 5", nativeQuery = true)
    List<LastFiveStudents> getLastFiveStudents();
}
