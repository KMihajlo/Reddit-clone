package com.mkraguje.redditclone.repository;

import com.mkraguje.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
