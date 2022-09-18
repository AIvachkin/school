package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testStart() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/info", String.class))
                .isEqualTo("Приложение запущено!");
    }

    @Test
    public void testGetAllStudents() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/all", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentById() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/1", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "?20", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAgeBetweenPositive() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/ageBetween?ageMin=21,ageMax=23", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudentAndGetStudentsByAgeAndAgeBetween() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Ivan");
        student.setAge(999);

        Assertions
                .assertThat(this.restTemplate.postForObject(
                        "http://localhost:" + port + "/student", student, String.class))
                .isNotNull();


        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "?999", String.class))
                .isNotNull();

        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/ageBetween?ageMin=990,ageMax=1000", String.class))
                .isNotNull();

    }


    @Test
    public void testDeleteStudent() throws Exception {

        String url = "/student/6";
        ResponseEntity<Void> resp = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

    }
}
