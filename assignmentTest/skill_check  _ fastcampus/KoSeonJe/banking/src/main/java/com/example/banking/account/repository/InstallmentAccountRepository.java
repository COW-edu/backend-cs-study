package com.example.banking.account.repository;

import com.example.banking.account.domain.InstallmentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentAccountRepository extends JpaRepository<InstallmentAccount, Long> {

}
