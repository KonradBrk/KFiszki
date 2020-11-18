package com.konrad.brk.KFiszki.repository;

import com.konrad.brk.KFiszki.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findRoleByName(String roleName);
}
