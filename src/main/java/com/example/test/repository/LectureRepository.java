package com.example.test.repository;

import com.example.test.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Query(value = "SELECT c.*, count(student_id) as amount_of_students FROM LECTURES l LEFT JOIN COURSES c on l.course_id = c.id GROUP BY l.course_id", nativeQuery = true)
    List<Map<String, Object>> getCourses();
}
