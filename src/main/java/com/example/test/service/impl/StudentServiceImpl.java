package com.example.test.service.impl;

import com.example.test.assembler.StudentAssembler;
import com.example.test.dto.StudentDto;
import com.example.test.dto.StudentReqDto;
import com.example.test.entity.Student;
import com.example.test.exception.ObjectNotFoundException;
import com.example.test.exception.StudentAlreadyExistedException;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.repository.StudentRepository;
import com.example.test.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private StudentAssembler studentAssembler;
    @Override
    public StudentDto findById(Long id) throws StudentNotFoundException {

        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        return studentAssembler.toDto(student);
    }

    @Override
    public StudentDto add(StudentReqDto body) throws StudentAlreadyExistedException{
        // check validation by its email
        try {
            if (studentRepository.findByEmail(body.getEmail()).isPresent()) {
                throw new StudentAlreadyExistedException();
            }
        } catch (Exception ex) {
            throw new StudentAlreadyExistedException();
        }
        // get entity
        Student student = studentAssembler.toEntity(body);
        // save & resturn the data with its generated id as well.
        return studentAssembler.toDto(studentRepository.save(student));
    }

    @Override
    public StudentDto update(StudentReqDto body) throws ObjectNotFoundException {
        // check validation by its id
        Student student = studentRepository.findById(body.getId())
                .orElseThrow(StudentNotFoundException::new);
        // check validation by its email
        try {
            if (studentRepository.findByEmail(body.getEmail()).isPresent()) {
                throw new StudentAlreadyExistedException();
            }
        } catch (Exception ex) {
            throw new StudentAlreadyExistedException();
        }
        // update data
        student.setFirstName(body.getFirstName());
        student.setLastName(body.getLastName());
        student.setEmail(body.getEmail());

        // save & resturn the data with its generated id as well.
        return studentAssembler.toDto(studentRepository.save(student));
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(student -> studentAssembler.toDto(student)).toList();
    }
}
