package com.example.test.controller;

import com.example.test.dto.ErrorResponse;
import com.example.test.dto.StudentDto;
import com.example.test.dto.StudentReqDto;
import com.example.test.entity.Student;
import com.example.test.exception.ObjectNotFoundException;
import com.example.test.exception.StudentAlreadyExistedException;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.service.StudentService;
import com.example.test.utils.DateAndTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@EnableScheduling
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * Getting Student including the list of courses by student_id
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable Long id) {
        try{
            StudentDto studentDto = studentService.findById(id);
            return ResponseEntity.ok(studentDto);
        } catch (StudentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                        ErrorResponse.builder()
                            .code(ex.getCode())
                            .message(ex.getMessage())
                            .status(HttpStatus.BAD_REQUEST.value())
                            .timestamp(DateAndTimeUtil.getCurrentEpochTime())
                            .build()
                    );
        }
    }

    /**
     * add a new student : it will return the student data with its id generated.
     * @param body
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody StudentReqDto body) {

        StudentDto studentDto = null;
        try {
            studentDto = studentService.add(body);

            return ResponseEntity.ok(studentDto);
        } catch (StudentAlreadyExistedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ErrorResponse.builder()
                                    .code(ex.getCode())
                                    .message(ex.getMessage())
                                    .status(HttpStatus.BAD_REQUEST.value())
                                    .timestamp(DateAndTimeUtil.getCurrentEpochTime())
                                    .build()
                    );
        }
    }

    /**
     * update the existing the student
     * @param body
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody StudentReqDto body) {
        try{
            StudentDto studentDto = studentService.update(body);
            return ResponseEntity.ok(studentDto);
        } catch (ObjectNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ErrorResponse.builder()
                                    .code(ex.getCode())
                                    .message(ex.getMessage())
                                    .status(HttpStatus.BAD_REQUEST.value())
                                    .timestamp(DateAndTimeUtil.getCurrentEpochTime())
                                    .build()
                    );
        }
    }

    /**
     * get the list of students
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }
}
