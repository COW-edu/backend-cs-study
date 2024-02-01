package com.example.banking.bank.controller;

import com.example.banking.account.controller.dto.response.AccountResponse;
import com.example.banking.bank.controller.dto.request.CreateBankRequest;
import com.example.banking.bank.controller.dto.request.DepositRequest;
import com.example.banking.bank.controller.dto.request.TransferMoneyRequest;
import com.example.banking.bank.controller.dto.request.WithDrawRequest;
import com.example.banking.bank.service.BankService;
import com.example.banking.common.api.response.ResponseMessage;
import com.example.banking.common.api.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BankController {

  private final BankService bankService;

  @PostMapping("/bank")
  public ResponseEntity<ResponseMessage> createBank(@RequestBody CreateBankRequest createBankRequest) {
    bankService.createBank(createBankRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK), HttpStatus.OK);
  }

  @PatchMapping("/transfer")
  public ResponseEntity<ResponseMessage> transferMoney(@RequestBody TransferMoneyRequest transferMoneyRequest) {
    AccountResponse accountResponse = bankService.transferMoney(transferMoneyRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK, accountResponse), HttpStatus.OK);
  }
  @PatchMapping("/deposit")
  public ResponseEntity<ResponseMessage> deposit(@RequestBody DepositRequest depositRequest) {
    AccountResponse accountResponse = bankService.deposit(depositRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK, accountResponse),
        HttpStatus.OK);
  }

  @PatchMapping("/withdrawal")
  public ResponseEntity<ResponseMessage> withdraw(@RequestBody WithDrawRequest withDrawRequest) {
    AccountResponse accountResponse = bankService.withDraw(withDrawRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK, accountResponse),
        HttpStatus.OK);
  }
}
