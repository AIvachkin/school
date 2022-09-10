package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    default Collection<Student> findByAge(int age) {

        return findAll().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());

    }
}
