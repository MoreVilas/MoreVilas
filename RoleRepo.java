package com.collegefes.CollegeFest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collegefes.CollegeFest.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{

}
