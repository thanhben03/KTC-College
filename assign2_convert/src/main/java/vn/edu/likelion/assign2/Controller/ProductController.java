package vn.edu.likelion.assign2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.assign2.AuthAnotation.CheckLogin;
import vn.edu.likelion.assign2.Entity.ProductEntity;
import vn.edu.likelion.assign2.Service.Product.ProductService;
import vn.edu.likelion.assign2.Service.Warehouse.WarehouseService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CheckLogin
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<ProductEntity> create(@RequestBody ProductEntity product) {
        try {
            productService.create(product);
        } catch (Exception e) {
            return null;
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Optional<ProductEntity> findById(@PathVariable("id") int id) {
        ProductEntity user = new ProductEntity();
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductEntity update(@PathVariable("id") int id, @RequestBody ProductEntity request) {
        return productService.update(id, request);
    }

    @GetMapping
    public List<ProductEntity> findAll() {
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            Optional<ProductEntity> productEntity = productService.getById(id);
            productEntity.ifPresent(entity -> productService.delete(entity));
            return ResponseEntity.status(200).body("Delete success !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @GetMapping("/by-warehouse/{warehouseId}")
    public ResponseEntity<?> findAllByWarehouseId(@PathVariable("warehouseId") int warehouseId) {
        try {
            List<ProductEntity> products = productService.findAllByWarehouseId(warehouseId);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/import-excel")
    public ResponseEntity<?> importExcel(@RequestBody Map<String, Object> payload) {
        Boolean result = productService.importExcel(payload);

        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Imported Successfully !");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Import Error !");
    }



}
