package vn.edu.likelion.assign2.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity{

    // Dong goi Entity thanh byte Gui entity cho web se nhanh hon

    @Column(unique = true, nullable = false)
    @NonNull
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "role_id", nullable = false)
    private int roleId;

    public @NonNull String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
