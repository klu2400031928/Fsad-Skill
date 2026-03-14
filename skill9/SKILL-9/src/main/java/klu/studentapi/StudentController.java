package klu.studentapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {

        // Validate if id is a valid number
        long studentId;
        try {
            studentId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: '" + id + "' is not a valid student ID. Please provide a number.");
        }

        // Return sample student data for id = 1
        if (studentId == 1) {
            Student student = new Student(1L, "Arun Kumar", "Computer Science");
            return ResponseEntity.ok(student);
        }

        // Throw exception for any other id
        throw new StudentNotFoundException("Student not found with ID: " + studentId);
    }
}
