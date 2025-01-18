package io.reflectoring.ActiveLogin.service.Impl;

import io.reflectoring.ActiveLogin.dto.StudentDto;
import io.reflectoring.ActiveLogin.exception.ResourceNotFoundException;
import io.reflectoring.ActiveLogin.mapper.StudentMapper;
import io.reflectoring.ActiveLogin.models.Student;
import io.reflectoring.ActiveLogin.repository.StudentRepository;
import io.reflectoring.ActiveLogin.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.reflectoring.ActiveLogin.mapper.StudentMapper.*;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;


    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student = Student.builder()
                .tkmId(studentDto.getTkmId())
                .year(studentDto.getYear())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .rollNo(studentDto.getRollNo())
                .actpts(studentDto.getActpts()) // This will be null if not provided in DTO
                .build();

        // Save to database
        return studentRepository.save(student);
    }

    @Override
    public StudentDto getStudentById(Long regid) {
        Student student = studentRepository.findById(regid)
                .orElseThrow(()-> new ResourceNotFoundException("Student with Id not found"));

        return mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long regId, StudentDto updatedDet) {
        Student student = studentRepository.findById(regId).orElseThrow(
                () -> new ResourceNotFoundException("Student with Id not found:"+regId)
        );

        updateStudentFromDto(updatedDet, student);

        Student updatedStudent = studentRepository.save(student);

        return mapToStudentDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long regId) {
        Student student = studentRepository.findById(regId).orElseThrow(
                () -> new ResourceNotFoundException("Student with Id not found:"+regId)
        );
        studentRepository.deleteById(regId);
    }
}
