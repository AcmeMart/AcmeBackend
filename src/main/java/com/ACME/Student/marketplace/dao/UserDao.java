package com.ACME.Student.marketplace.dao;

import com.ACME.Student.marketplace.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
