package vn.likelion.manage_student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.likelion.manage_student.Entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
}