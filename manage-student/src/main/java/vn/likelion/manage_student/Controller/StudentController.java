package vn.likelion.manage_student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.likelion.manage_student.Entity.StudentEntity;
import vn.likelion.manage_student.Model.Request.StudentDTO;
import vn.likelion.manage_student.Service.StudentService;
import vn.likelion.manage_student.Util.ResponseHandler;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findAll(@PathVariable int id) {
        try {
            StudentEntity studentEntity = studentService.find(id);
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Get Successfully !", studentEntity);
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.BAD_REQUEST, true, "Get Failed !", "");
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody StudentEntity entity) {
        try {

            StudentEntity studentEntity = studentService.create(entity);

            return ResponseHandler
                    .generateResponse(HttpStatus.OK, false, "Create Success !", studentEntity);
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(HttpStatus.BAD_REQUEST, false, "Create Failed !", e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<StudentEntity> studentEntities = studentService.findAll();
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Find Success !", studentEntities);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Failed !", e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id , @RequestBody StudentEntity studentEntity) {
        try {
            StudentEntity student = studentService.update(id, studentEntity);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update Success !", student);
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(HttpStatus.NOT_FOUND, false, "Update Failed !", "");

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            StudentEntity studentEntity = studentService.find(id);
            studentService.delete(studentEntity);

            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Delete Success !", studentEntity);

        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(HttpStatus.NOT_FOUND, false, "Update Failed !", e.getMessage());

        }
    }
}
