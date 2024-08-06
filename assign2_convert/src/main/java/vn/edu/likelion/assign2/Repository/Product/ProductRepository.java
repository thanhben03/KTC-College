package vn.edu.likelion.assign2.Repository.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.likelion.assign2.Entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByWarehouseId(int warehouseId);
}
