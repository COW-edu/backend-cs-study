package com.example.banking.account.controller;

import com.example.banking.account.controller.dto.request.CreateInstallmentAccountRequest;
import com.example.banking.account.controller.dto.request.CreateSavingsAccountRequest;
import com.example.banking.account.controller.dto.request.GetAccountRequest;
import com.example.banking.account.controller.dto.response.AccountResponse;
import com.example.banking.account.service.AccountService;
import com.example.banking.common.api.response.ResponseMessage;
import com.example.banking.common.api.response.ResponseStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/accounts")
  public ResponseEntity<ResponseMessage> getAccounts() {
    List<AccountResponse> accountsResponses = accountService.getAccounts();
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK, accountsResponses), HttpStatus.OK);
  }

  @GetMapping("/account")
  public ResponseEntity<ResponseMessage> getAccount(@RequestBody GetAccountRequest getAccountRequest) {
    AccountResponse accountsResponse = accountService.getAccount(getAccountRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK, accountsResponse), HttpStatus.OK);
  }

  @PostMapping("/savings-account")
  public ResponseEntity<ResponseMessage> createSavingsAccount(
      @RequestBody CreateSavingsAccountRequest createSavingsAccountRequest) {
    accountService.createSavingsAccount(createSavingsAccountRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK), HttpStatus.OK);
  }

  @PostMapping("/installment-account")
  public ResponseEntity<ResponseMessage> createInstallmentAccount(
      @RequestBody CreateInstallmentAccountRequest createInstallmentAccountRequest) {
    accountService.createInstallmentAccount(createInstallmentAccountRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK), HttpStatus.OK);
  }
}
