package tn.esprit.course.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.course.entities.Course;
import tn.esprit.course.services.ICoursService;
import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*")
public class CourseRestController {
    @Autowired
    private ICoursService courseService;
    //method to get all courses
    @GetMapping("/get_all-course")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/add_course")
    public ResponseEntity<Course> addCourse(
            @RequestParam("namecourse") String namecourse,
            @RequestParam("description") String description,
            @RequestParam("nbhours") int nbhours,
            @RequestParam("file") MultipartFile file

    ) {
        try {


            Course course = new Course();
            course.setNamecourse(namecourse);
            course.setDescription(description);
            course.setNbhours(nbhours);


            Course savedCourse = courseService.addCourse(course, file);
            return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_course_byid/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {
        return courseService.getCourseById(id);
    }

    @DeleteMapping("/delete_course/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }

    @PutMapping("/update_course/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable("id") Long id,
            @RequestParam(value = "namecourse", required = false) String namecourse,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "nbhours", required = false) Integer nbhours,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        Course updatedCourse = courseService.updateCourse(id, namecourse, description, nbhours, file);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

}
