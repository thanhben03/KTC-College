package vn.likelion.manage_student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.likelion.manage_student.Entity.ClassRoomEntity;

public interface ClassRoomRepository extends JpaRepository<ClassRoomEntity, Integer> {
}
