package org.example;

public class Application {

  public static void main(String[] args) {
    AppConfig appConfig = new AppConfig();
    BankApplication bankApplication = appConfig.bankApplication();
    bankApplication.run();
  }
}