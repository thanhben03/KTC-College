package vn.edu.likelion.assign2.Configuration;

import org.springframework.stereotype.Component;
import vn.edu.likelion.assign2.Entity.UserEntity;

@Component
public class SessionConfig {

    private UserEntity userEntity = null;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
