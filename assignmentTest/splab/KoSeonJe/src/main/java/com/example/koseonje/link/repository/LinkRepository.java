package com.example.koseonje.link.repository;

import com.example.koseonje.link.domain.Link;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
  Optional<Link> findByAccessKey(UUID key);
}

