package aiss.github.service;

import aiss.github.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService service;


    @Test
    @DisplayName("Get project by its owner and repo name")
    void getProject() {
        Project project = service.getProject("spring-projects", "spring-framework", 2, 20, 2);
        assertTrue(!project.equals(null), "The project is null");
        System.out.println(project);
    }
}