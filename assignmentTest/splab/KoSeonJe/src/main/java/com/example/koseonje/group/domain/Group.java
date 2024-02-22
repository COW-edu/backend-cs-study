package com.example.koseonje.group.domain;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.example.koseonje.member.domain.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GROUP_TABLE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "group_id")
  private Long id;

  @Column(unique = true)
  private String name;

  @OneToMany(mappedBy = "group", cascade = {CascadeType.ALL})
  private List<Member> members = new ArrayList<>();

  public void addMember(final Member member) {
    this.members.add(member);
  }
}
