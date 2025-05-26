package tn.esprit.course.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.course.entities.Course;

import tn.esprit.course.repositories.CourseRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements ICoursService {

    private final CourseRepository courseRepository;

    private final FileStorageService fileStorageService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             FileStorageService fileStorageService
                            ) {
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;

    }





    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Course addCourse(Course course, MultipartFile file) throws IOException {
        String fileUrl = fileStorageService.storeFile(file);
        course.setPdfcourse(fileUrl);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, String namecourse, String description, Integer nbhours, MultipartFile file) throws IOException {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        if (namecourse != null) {
            existingCourse.setNamecourse(namecourse);
        }
        if (description != null) {
            existingCourse.setDescription(description);
        }
        if (nbhours != null) {
            existingCourse.setNbhours(nbhours);
        }
        if (file != null && !file.isEmpty()) {
            String fileUrl = fileStorageService.storeFile(file);
            existingCourse.setPdfcourse(fileUrl);
        }
        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}