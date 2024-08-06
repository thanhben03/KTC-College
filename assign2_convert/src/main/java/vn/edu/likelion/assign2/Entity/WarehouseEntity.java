package vn.edu.likelion.assign2.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Table(name = "warehouses")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntity extends BaseEntity{

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "user_id")
    private int userId;

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
