package com.example.koseonje.group.repository;

import com.example.koseonje.group.domain.Group;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
  Optional<Group> findByName(String name);
}
