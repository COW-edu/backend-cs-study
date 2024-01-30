package org.example;

import org.example.bank.BankManager;
import org.example.view.ApplicationView;

public class BankApplication implements Runnable {

  private final ApplicationView applicationView;
  private final BankManager bankManager;

  public BankApplication(ApplicationView applicationView, BankManager bankManager) {
    this.applicationView = applicationView;
    this.bankManager = bankManager;
  }

  @Override
  public void run() {
    int processNumber = selectProcess();
    executeProcess(processNumber);
  }

  private int selectProcess() {
    return applicationView.selectProcess();
  }

  private void executeProcess(int processNumber) {
    bankManager.service(processNumber);
  }
}
