package com.example.banking.bank.repostiory;

import com.example.banking.bank.domain.Bank;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank,Long> {

  Optional<Bank> findByName(String name);
}
