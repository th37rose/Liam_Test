package com.example.test.controller;

import com.example.test.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableScheduling
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final LectureService lectureService;


    /**
     * get the list of courses with amount of students in each course.
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAllCourses() {
        return ResponseEntity.ok(lectureService.getCourses());
    }
}
