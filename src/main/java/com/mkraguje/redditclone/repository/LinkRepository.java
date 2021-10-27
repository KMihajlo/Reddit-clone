package com.mkraguje.redditclone.repository;

import com.mkraguje.redditclone.model.Link;
import com.mkraguje.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByUser(User user);
}
