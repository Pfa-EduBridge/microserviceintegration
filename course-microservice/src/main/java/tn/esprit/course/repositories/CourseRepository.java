package tn.esprit.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.course.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}