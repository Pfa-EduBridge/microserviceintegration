package tn.esprit.course.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.course.entities.Course;


import java.io.IOException;
import java.util.List;

public interface ICoursService {
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course addCourse(Course course, MultipartFile file) throws IOException;
    Course updateCourse(Long id, String namecourse, String description, Integer nbhours, MultipartFile file) throws IOException;
    void deleteCourse(Long id);

}
