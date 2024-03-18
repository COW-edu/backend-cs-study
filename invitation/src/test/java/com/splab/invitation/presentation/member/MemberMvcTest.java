package com.splab.invitation.presentation.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.splab.invitation.domain.member.service.MemberService;
import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class MemberMvcTest {

  private static final String BASE_URI = "/members/invitation";

  private MockMvc mockMvc;
  private final ObjectMapper objectMapper = new ObjectMapper();
  @InjectMocks
  private MemberController memberController;
  @Mock
  private MemberService memberService;

  @BeforeEach
  public void init() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(memberController)
        .build();
  }

  @DisplayName("정상적인 값일 때 멤버 초대")
  @Test
  void invite() throws Exception {
    // given
    InviteMemberCommand command = new InviteMemberCommand("test", "test", "test", "test");
    String content = objectMapper.writeValueAsString(command);

    // when & then
    mockMvc.perform(
            post(BASE_URI + ":send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        )
        .andExpect(status().isCreated());
  }

  @DisplayName("정상적인 값일 때 초대 수락")
  @Test
  void join() throws Exception {
    // given
    String email = "rhrjs0131@naver.com";
    String invitationCode = "123456";

    // when & then
    mockMvc.perform(
            get(BASE_URI + ":join")
                .param("email", email)
                .param("invitationCode", invitationCode)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
  }
}
