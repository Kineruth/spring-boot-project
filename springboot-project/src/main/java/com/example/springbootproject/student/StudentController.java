package com.example.springbootproject.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Dependency Injection:
Avoid ... = new ...(); and use only this.s = s; we use annotation @Autowired
saying (s) should be auto wired for us, magically instantiated for us & injected to the constructor.
 */
@RestController  //makes this class to serve restful endpoints
@RequestMapping(path = "api/v1/student") //localhost:8080/...
public class StudentController {  //will serve as the API Layer (get,post,put,delete)

    private final StudentService studentService; //this is the spring bean to be autowired

    @Autowired
    StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping  //serves as a restful endpoint
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    // we take the request body & map it into student
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") Long studentID){
        studentService.deleteStudent(studentID);
    }

    @PutMapping(path = "{studentID}")
    public void updateStudent(@PathVariable("studentID") Long studentID,
                              @RequestParam(required = false)String name,
                              @RequestParam(required = false)String email){
        studentService.updateStudent(studentID, name, email);
    }
}

