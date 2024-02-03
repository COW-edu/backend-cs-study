package com.example.banking.account.repository;

import com.example.banking.account.domain.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByAccountNumber(String accountNumber);

  boolean existsByAccountNumber(String accountNumber);

}
