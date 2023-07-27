package com.example.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code LectureService} interface provides methods in order to find, save and update lecture(s).
 */
public interface LectureService {
    List<Map<String, Object>> getCourses();
}
