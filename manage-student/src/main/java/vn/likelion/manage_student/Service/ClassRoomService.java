package vn.likelion.manage_student.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.likelion.manage_student.Entity.ClassRoomEntity;
import vn.likelion.manage_student.Entity.StudentEntity;
import vn.likelion.manage_student.Repository.ClassRoomRepository;
import vn.likelion.manage_student.Repository.StudentRepository;

import java.util.List;

@Service
public class ClassRoomService {
    @Autowired
    ClassRoomRepository classRoomRepository;


    public ClassRoomEntity create(ClassRoomEntity classRoomEntity) {

        return classRoomRepository.save(classRoomEntity);
    }

    public List<ClassRoomEntity> findAll() {
        return classRoomRepository.findAll();
    }

    public ClassRoomEntity find(int classRoomId) {
        return classRoomRepository.findById(classRoomId).get();
    }

    public void delete(ClassRoomEntity studentEntity) {
        classRoomRepository.delete(studentEntity);
    }

    public ClassRoomEntity update(int id, ClassRoomEntity classRoomEntity) {


        ClassRoomEntity classRoomUpdate = classRoomRepository.findById(id).get();

        classRoomUpdate.setClassName(classRoomEntity.getClassName());
        classRoomUpdate.setQuantity(classRoomEntity.getQuantity());

        return classRoomRepository.save(classRoomUpdate);
    }
}
