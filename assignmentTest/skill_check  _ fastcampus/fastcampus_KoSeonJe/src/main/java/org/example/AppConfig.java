package org.example;

import org.example.bank.service.BankService;
import org.example.bank.service.BankServiceImpl;
import org.example.bank.BankManager;
import org.example.view.ApplicationView;

public class AppConfig {
//    InterestPolicy InterestPolicy = new InterestRatePolicy();

  public BankApplication bankApplication() {
    return new BankApplication(applicationView(), bankManager());
  }

  private ApplicationView applicationView() {
    return new ApplicationView();
  }

  private BankManager bankManager() {
    return new BankManager(bankService());
  }

  private BankService bankService() {
    return new BankServiceImpl();
  }
}
