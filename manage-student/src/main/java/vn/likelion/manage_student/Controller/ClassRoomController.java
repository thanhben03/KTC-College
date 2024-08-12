package vn.likelion.manage_student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.likelion.manage_student.Entity.ClassRoomEntity;
import vn.likelion.manage_student.Entity.StudentEntity;
import vn.likelion.manage_student.Service.ClassRoomService;
import vn.likelion.manage_student.Service.StudentService;
import vn.likelion.manage_student.Util.ResponseHandler;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ClassRoomController {
    @Autowired
    ClassRoomService classRoomService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findAll(@PathVariable int id) {
        try {
            ClassRoomEntity classRoomEntity = classRoomService.find(id);
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Get Successfully !", classRoomEntity);
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.BAD_REQUEST, true, "Get Failed !", "");
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ClassRoomEntity entity) {
        try {

            ClassRoomEntity classRoomEntity = classRoomService.create(entity);

            return ResponseHandler
                    .generateResponse(HttpStatus.OK, false, "Create Success !", classRoomEntity);
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(HttpStatus.BAD_REQUEST, false, "Create Failed !", e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<ClassRoomEntity> classRoomEntities = classRoomService.findAll();
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Find Success !", classRoomEntities);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Failed !", e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id , @RequestBody ClassRoomEntity classRoomEntity) {
        try {
            ClassRoomEntity classRoom = classRoomService.update(id, classRoomEntity);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update Success !", classRoom);
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(HttpStatus.NOT_FOUND, false, "Update Failed !", "");

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            ClassRoomEntity studentEntity = classRoomService.find(id);
            classRoomService.delete(studentEntity);

            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Delete Success !", studentEntity);

        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(HttpStatus.NOT_FOUND, false, "Update Failed !", e.getMessage());

        }
    }
}
