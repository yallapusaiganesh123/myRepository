package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.UserAdmin;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, String> {

}
