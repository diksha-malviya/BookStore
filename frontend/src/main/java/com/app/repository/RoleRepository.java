package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.ERole;
import com.app.pojos.Role;



public interface RoleRepository  extends JpaRepository<Role, Long>{
 Optional<Role> findByName(ERole name);
}
