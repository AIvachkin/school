package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.constants.StudentServiceConstants;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.hogwarts.school.constants.StudentServiceConstants.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

//    @Mock
//    private final StudentRepository studentRepositoryMock = mock(StudentRepository.class);
//
//    @InjectMocks
//    private StudentService out;
//
//    @BeforeEach
//    public void initOut(){
//        out = new StudentService(studentRepositoryMock);
//    }

    @Mock
    private final StudentRepository studentRepositoryMock = mock(StudentRepository.class);

    @InjectMocks
    private StudentService studentService = new StudentService(studentRepositoryMock);


//    @Test
//    public void createStudentTest() {
//
//
//        when(studentService.createStudent(STUDENT1)).thenReturn(STUDENT1);
////        when(studentService.findStudent(1)).thenReturn(STUDENT1);
////        when(studentService.editStudent(1, STUDENT2)).thenReturn(STUDENT2);
////        when(studentServiceMoteStudent(STUDENT5)).thenReturn(STUDENT5);
////        Optional<Student> opt = Optional.ofNullable(studentService.findStudent(1));
////        Student actualStudent = opt.get();
//
//        assertEquals (STUDENT1, studentService.createStudent(STUDENT1));
////        assertEquals (STUDENT1, studentService.findStudent(1));
//
////        assertEquals (STUDENT1, actualStudent);
//
//    }

}
