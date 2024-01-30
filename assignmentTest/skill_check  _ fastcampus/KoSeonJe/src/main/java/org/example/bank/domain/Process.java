package org.example.bank.domain;


import org.example.bank.service.BankService;
import org.example.bank.service.BankServiceImpl;

public enum Process {
  CREATE_ACCOUNT(1){
    public void execute(BankService bankService) {
      bankService.createBankAccount();
    }
  },
  CHECK_ACCOUNT_INFO(2){
    public void execute(BankService bankService) {
      bankService.checkAccountInfo();
    }
  },
  WITHDRAW(3){
    public void execute(BankService bankService) {
      bankService.withdraw();
    }
  },
  DEPOSIT(4){
    public void execute(BankService bankService) {
      bankService.deposit();
    }
  },
  TRANSFER(5){
    public void execute(BankService bankService) {
      bankService.transfer();
    }
  };

  private final int number;

  Process(int number) {
    this.number = number;
  }

  public int getNumber() {
    return this.number;
  }

  public abstract void execute(BankService bankService);
}
