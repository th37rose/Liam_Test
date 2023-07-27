package com.example.test.assembler;

import com.example.test.dto.CourseDto;
import com.example.test.dto.StudentDto;
import com.example.test.dto.StudentReqDto;
import com.example.test.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentAssembler {
    public StudentDto toDto(Student student) {
        if(student.getLectures() == null) {             // insert
            return StudentDto.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .email(student.getEmail())
                    .build();
        }
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .courses(student.getLectures().stream()
                        .map(lecture -> CourseDto.builder()
                                .id(lecture.getCourse().getId())
                                .courseName(lecture.getCourse().getCourseName())
                                .courseDescription(lecture.getCourse().getCourseDescription())
                                .creditHours(lecture.getCourse().getCreditHours())
                                .maxStudents(lecture.getCourse().getMaxStudents())
                                .build()
                        ).toList())
                .build();
    }

    public Student toEntity(StudentReqDto body) {
        return Student.builder()
                .id(body.getId())
                .firstName(body.getFirstName())
                .lastName(body.getLastName())
                .email(body.getEmail())
                .build();
    }
}
