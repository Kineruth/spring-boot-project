package com.example.springbootproject.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //for autowired, it knows where to find the Spring bean. This is the Service Layer
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //returns a List (as JSON format on the website)
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional <Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email's already taken");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentID) {
        boolean exists = studentRepository.existsById(studentID);
        if(!exists){
            throw new IllegalStateException("Student (id: " + studentID + ") does not exist");
        }

        studentRepository.deleteById(studentID);
    }

    @Transactional //transforms code into JPQL queries
    public void updateStudent(Long studentID, String name, String email) {
        Student student = studentRepository.findById(studentID).orElseThrow(
                ()-> new IllegalStateException("Student (id: " + studentID + ") does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(name, student.getName())){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())){
            Optional <Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email's already taken");
            }
            student.setEmail(email);
        }
    }
}



