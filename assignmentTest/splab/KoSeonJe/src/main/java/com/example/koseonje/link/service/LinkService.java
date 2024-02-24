package com.example.koseonje.link.service;

import com.example.koseonje.link.domain.Link;
import com.example.koseonje.link.repository.LinkRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LinkService {

  private final LinkRepository linkRepository;

  @Transactional
  public void save(Link link) {
    this.linkRepository.save(link);
  }

  public Link findByKey(UUID accessKey) {
    return linkRepository.findByAccessKey(accessKey)
        .orElseThrow(() -> new IllegalArgumentException("찾으시는 링크는 존재하지 않습니다"));
  }
}
