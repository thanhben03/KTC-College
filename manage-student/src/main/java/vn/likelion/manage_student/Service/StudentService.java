package vn.likelion.manage_student.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.likelion.manage_student.Entity.StudentEntity;
import vn.likelion.manage_student.Model.Request.StudentDTO;
import vn.likelion.manage_student.Repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;


    public StudentEntity create(StudentEntity studentEntity) {

        return studentRepository.save(studentEntity);
    }

    public List<StudentEntity> findAll() {
        return studentRepository.findAll();
    }

    public StudentEntity find(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    public void delete(StudentEntity studentEntity) {
        studentRepository.delete(studentEntity);
    }

    public StudentEntity update(int id, StudentEntity studentEntity) {


        StudentEntity studentUpdate = studentRepository.findById(id).get();

        studentUpdate.setStudentName(studentEntity.getStudentName());
        studentUpdate.setBirthday(studentEntity.getBirthday());
        studentUpdate.setClassRoomId(studentEntity.getClassRoomId());

        return studentRepository.save(studentEntity);
    }
}
