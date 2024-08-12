package vn.likelion.manage_student.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_rooms")
@Data
public class ClassRoomEntity extends BaseEntity{
    @Column(name = "class_name", unique = true, nullable = false)
    private String className;

    @Column(name = "quantity", columnDefinition = "int default 0")
    private int quantity;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
