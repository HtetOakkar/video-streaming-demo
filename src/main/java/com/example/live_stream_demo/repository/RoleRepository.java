package com.example.live_stream_demo.repository;

import com.example.live_stream_demo.model.entity.Role;
import com.example.live_stream_demo.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT r FROM Role r WHERE r.name = :name")
    Role findByName(RoleName name);
}
