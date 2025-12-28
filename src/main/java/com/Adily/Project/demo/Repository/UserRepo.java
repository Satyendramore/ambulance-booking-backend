package com.Adily.Project.demo.Repository;

import com.Adily.Project.demo.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String name);

}
