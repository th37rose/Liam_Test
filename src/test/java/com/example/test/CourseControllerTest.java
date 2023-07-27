package com.example.test;

import com.example.test.controller.CourseController;
import com.example.test.controller.StudentController;
import com.example.test.service.LectureService;
import com.example.test.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { MockServletContext.class })
public class CourseControllerTest {
    private final String baseURL = "/api/v1/courses";
    private MockMvc mockMvc;

    private LectureService lectureService;

    @Before
    public void init() {
        lectureService = mock(LectureService.class);

        CourseController controller = new CourseController(lectureService);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void getListOfCoursesTest() throws Exception {
        mockMvc.perform(get(baseURL.concat("/")).contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}
