package com.example.koseonje.group.service;

import com.example.koseonje.group.domain.Group;
import com.example.koseonje.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

  private final GroupRepository groupRepository;

  public Group findByName(String name) {
    return groupRepository.findByName(name)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 그룹 이름입니다."));
  }
}
