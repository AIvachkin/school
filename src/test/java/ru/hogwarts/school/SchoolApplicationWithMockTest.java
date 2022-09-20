package ru.hogwarts.school;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FacultyController.class)
public class SchoolApplicationWithMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void testGetFaculty() throws Exception {
        Long id = 1L;
        String name = "Tree";
        String color = "green";

        Faculty faculty = new Faculty(id, name, color);
//        List<Faculty> facultyList = List.of(faculty);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

    }

    @Test
    public void findFacultiesByColor() throws Exception {

        Long id1 = 1L;
        String name1 = "Tree";

        Long id2 = 2L;
        String name2 = "Flower";

        String color = "green";

        Faculty faculty1 = new Faculty(id1, name1, color);
        Faculty faculty2 = new Faculty(id2, name2, color);

        when(facultyRepository.findByColor(color)).thenReturn(List.of(faculty1, faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam(color, color)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1, faculty2))));
    }

    @Test
    public void testFindFacultiesByColorOrNameIgnoreCase() throws Exception {

        Long id1 = 1L;
        String name1 = "Tree";
        String color1 = "YelloW";
        String color1IgnoreCase = "yellow";

        Long id2 = 2L;
        String name2 = "Flower";
        String name2IgnoreCase = "FloWER";
        String color2 = "green";

        Faculty faculty1 = new Faculty(id1, name1, color1);
        Faculty faculty2 = new Faculty(id2, name2, color2);

        when(facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color1IgnoreCase, name2IgnoreCase))
                .thenReturn(List.of(faculty1, faculty2));


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("color", color1IgnoreCase)
                        .queryParam("name", name2IgnoreCase)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1, faculty2))));
    }

    @Test
    public void testAddFaculty() throws Exception {

        Long id = 1L;
        String name = "Tree";
        String color = "green";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty(id, name, color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findFacultyById(id)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testUpdateFaculty() throws Exception {

        Long id = 1L;
        String oldName = "Tree";
        String oldColor = "green";

        String newName = "Water";
        String newColor = "Blue";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", newName);
        facultyObject.put("color", newColor);

        Faculty faculty = new Faculty(id, oldName, oldColor);

        Faculty updateFaculty = new Faculty();
        updateFaculty.setId(id);
        updateFaculty.setName(newName);
        updateFaculty.setColor(newColor);

        when(facultyRepository.findFacultyById(id)).thenReturn(faculty);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(updateFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(newName))
                .andExpect(jsonPath("$.color").value(newColor));
//
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .delete("/faculty/" + id)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long id = 1L;
        String name = "Tree";
        String color = "green";


        Faculty faculty = new Faculty(id, name, color);

        when(facultyRepository.findFacultyById(id)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        verify(facultyRepository, atLeastOnce()).deleteFacultyById(id);


    }


//    @Test
//    public void saveFacultyTest() throws Exception {
//        long id = 1L;
//        String name = "Fire";
//        String color = "Red";
//
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put(name, color);
//
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findFacultyById(any(Long.class))).thenReturn(faculty);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/faculty")
//                        .content(facultyObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//
//    }
//
//    @Test
//    public void getFacultyByIdTest() throws Exception {
//        long id = 1L;
//        String name = "Fire";
//        String color = "Red";
//
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put(name, color);
//
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        when(facultyRepository.findFacultyById(any(Long.class))).thenReturn(faculty);
////        when(facultyRepository.findFacultyById(any(FL))).thenReturn(faculty);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/faculty")
//                        .content(facultyObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//
//    }


}


