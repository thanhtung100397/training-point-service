package com.kthdv.training_point.dao;

import com.kthdv.training_point.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByRole(String role);
    boolean existsByIdAndRoleIn(String id, String[] roles);
}
