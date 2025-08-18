package com.example.library.management.system.repo;

import com.example.library.management.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
