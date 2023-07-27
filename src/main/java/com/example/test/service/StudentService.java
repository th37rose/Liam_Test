package com.example.test.service;

import com.example.test.dto.StudentDto;
import com.example.test.dto.StudentReqDto;
import com.example.test.entity.Student;
import com.example.test.exception.ObjectNotFoundException;
import com.example.test.exception.StudentAlreadyExistedException;
import com.example.test.exception.StudentNotFoundException;

import java.util.List;

/**
 * The {@code StudentService} interface provides methods in order to find, save and update student(s).
 */
public interface StudentService {
    /**
     * Returns the {@link StudentDto} object by {@code id}.
     *
     * @param id {@code Long} value specifying the unique identifier of the student.
     * @return the {@link StudentDto} object by {@code id}.
     * @throws StudentNotFoundException if user does not exists by specifying {@code email}.
     */
    StudentDto findById(Long id) throws StudentNotFoundException;

    StudentDto add(StudentReqDto body) throws StudentAlreadyExistedException;

    StudentDto update(StudentReqDto body) throws ObjectNotFoundException;

    List<StudentDto> getAll();
}
