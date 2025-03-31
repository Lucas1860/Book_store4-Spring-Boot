package com.try_store.demo_try_store.repository;

import com.try_store.demo_try_store.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
