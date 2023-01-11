package Dao;

import Entity.ProjectMentor;

import java.util.List;

public interface ProjectMentorDAO {
    List<ProjectMentor> getAllProjectMentors();
    void addProjectMentor(ProjectMentor projectMentor);


}
