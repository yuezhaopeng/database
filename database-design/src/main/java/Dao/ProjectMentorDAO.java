package Dao;


import Entity.ProjectMentor;

import java.util.List;

public interface ProjectMentorDAO {
    List<ProjectMentor> getAllProjectMentors();
    List<ProjectMentor> getProjectMentorsByMno(String Mno);
    List<ProjectMentor> getProjectMentorsByLno(String Lno);

    void addProjectMentor(ProjectMentor projectMentor);
    void deleteProjectMentorByPno(String Pno);

}
