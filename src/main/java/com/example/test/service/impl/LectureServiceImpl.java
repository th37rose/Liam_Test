package com.example.test.service.impl;

import com.example.test.repository.LectureRepository;
import com.example.test.service.LectureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {
    private LectureRepository lectureRepository;
    @Override
    public List<Map<String, Object>> getCourses() {
        return lectureRepository.getCourses();
    }
}
