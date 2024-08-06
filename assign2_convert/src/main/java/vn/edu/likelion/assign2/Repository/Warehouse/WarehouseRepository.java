package vn.edu.likelion.assign2.Repository.Warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.likelion.assign2.Entity.WarehouseEntity;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Integer> {
}
