package io.reflectoring.ActiveLogin.service;


import io.reflectoring.ActiveLogin.dto.StudentDto;
import io.reflectoring.ActiveLogin.models.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(StudentDto studentdto);
    StudentDto getStudentById(Long regid);
    List<StudentDto> getAllStudents();
    StudentDto updateStudent(Long regId,StudentDto updatedDet);
    void deleteStudent(Long regNo);
}
