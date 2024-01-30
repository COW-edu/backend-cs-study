package org.example.bank;

import org.example.bank.domain.Process;
import org.example.bank.service.BankService;

public class BankManager {

  private final BankService bankService;

  public BankManager(BankService bankService) {
    this.bankService = bankService;
  }

  public void service(int processNumber) {
    getProcess(processNumber).execute(bankService);
  }

  private Process getProcess(int processNumber) {
    for (Process process : Process.values()) {
      if (process.getNumber() == processNumber) {
        return process;
      }
    }
    throw new IllegalArgumentException("번호를 잘못 입력하였습니다.");
  }
}
