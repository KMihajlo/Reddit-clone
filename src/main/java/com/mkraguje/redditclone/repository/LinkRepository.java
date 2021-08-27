package com.mkraguje.redditclone.repository;

import com.mkraguje.redditclone.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
