package vn.edu.likelion.assign2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.assign2.Configuration.SessionConfig;
import vn.edu.likelion.assign2.Entity.UserEntity;
import vn.edu.likelion.assign2.Entity.WarehouseEntity;
import vn.edu.likelion.assign2.Service.User.UserService;
import vn.edu.likelion.assign2.Service.Warehouse.WarehouseService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody WarehouseEntity warehouse) {
        try {
            warehouseService.create(warehouse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Optional<WarehouseEntity> findById(@PathVariable("id") int id) {
        WarehouseEntity user = new WarehouseEntity();

        return warehouseService.getById(id);
    }

    @PutMapping("/{id}")
    public WarehouseEntity update(@PathVariable("id") int id, @RequestBody WarehouseEntity request) {
        return warehouseService.update(id, request);
    }

    @GetMapping
    public List<WarehouseEntity> findAll() {
        return warehouseService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            Optional<WarehouseEntity    > userEntity = warehouseService.getById(id);
            userEntity.ifPresent(entity -> warehouseService.delete(entity));
            return ResponseEntity.status(200).body("Delete success !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/export-excel")
    public ResponseEntity<?> exportExcel() {
        Boolean result = warehouseService.exportExcel();

        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Exported Successfully !");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Export Error !");
    }
}
