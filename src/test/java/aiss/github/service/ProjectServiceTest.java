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
    @DisplayName("Get all projects from an existing organisation")
    void findAllProjectsFromOrg() {
        String org = "Docker";
        List<Project> projects = service.findAllProjectsFromOrg(org);
        assertTrue(!projects.isEmpty(), "The list of projects is empty");
        System.out.println(projects);
    }

    @Test
    @DisplayName("Get a project by specifying its id")
    void findProject() {
        String projectId = "2443467";
        Project project = service.findProject(projectId);
        assertTrue(!project.equals(null), "The project is null");
        System.out.println(project);
    }
}