package com.splab.invitation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InvitationApplication {

  public static void main(String[] args) {
    SpringApplication.run(InvitationApplication.class, args);

  }

}
