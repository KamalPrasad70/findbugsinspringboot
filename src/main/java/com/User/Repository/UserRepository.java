package com.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.User.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
