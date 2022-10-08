package ru.hogwarts.school.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

//    @Mock
//    private final StudentRepository studentRepositoryMock = mock(StudentRepository.class);
//
//    @InjectMocks
//    private StudentServiceImpl out;
//
//    @BeforeEach
//    public void initOut(){
//        out = new StudentServiceImpl(studentRepositoryMock);
//    }

    @Mock
    private final StudentRepository studentRepositoryMock = mock(StudentRepository.class);

    @InjectMocks
    private StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentRepositoryMock);


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
