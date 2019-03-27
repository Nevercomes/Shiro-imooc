package com.nevercome.shiroweb.dao;

import com.nevercome.shiroweb.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    User getUserByUsername(String username);

    List<String> getRolesByUsername(String username);

    List<String> getPermissionsByUsername(String username);
}
