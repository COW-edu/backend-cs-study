# 1주차 - 뱅킹시스템 (패스트 캠퍼스)

### 설명

기본 예금 계좌, 적금 계좌를 가진 뱅킹 시스템 구현하기

### 요구사항

- 예금, 적금계좌의 차이를 다형성을 이용하여 구현 (이자율, 입출금 방법 등의 차이)
- 뱅킹 시스템이 가지는 기본기능 구현 (입/출금, 송금, 계좌 생성)
- 자바에서 제공하는 컬렉션 프레임워크 사용
- 예금/적금 상품 확장의 용이성을 위해 전략 패턴 사용

### 요구사항 X

- 금리의 변동은 고려하지 않음
- 은행 정기점검 시간에 따른 계좌 송금 가능 여부는 고려하지 않음
- 다른 은행 간 송금에 따른 수수료는 고려하지 않음
- 납입한도는 고려하지 않음
- 예금/적금의 중도해지는 고려하지 않음

### 가정한 것

- 계좌 하나 당 기본 계좌(상품 X) 혹은 상품을 하나씩만 설정할 수 있음

- 다양한 예금/적금 방식 중 하나씩 선택함
    - 예금: 보통예금 방식 (언제든 입출금 가능)
        - 연 이자율 - 2.0%
        - 12개월을 기준으로 함
    - 적금: 자유적금 방식 (언제든 자유롭게 적립 가능)
        - 입력한 만기일(3 ~ 12개월)에 따라 연 이자율이 달라짐
            - 3 ~ 6개월 - 2.0%
            - 6 ~ 12개월 - 3.0%

### 도메인 설계 / ERD

- 은행 (Bank)
    - 은행 이름
    - 계좌(list)

- 계좌 (Account)
    - 계좌 잔액
    - 계좌번호 (Unique)
    - 계좌 주인
    - 상품

- 상품 (Product) - enum으로 관리
    - 종류
    - 계좌번호
    - 연 이자율
    - 만기일

- ERD
  
    <img width="362" alt="스크린샷 2024-01-23 오후 3 35 31" src="https://github.com/COW-edu/backend-cs-study/assets/104254012/6aa1b557-e7b6-4100-86e4-80534d5ed422">
 

### API 설계

- Account
    - 계좌 잔액 조회
        
         `GET - /api/v1/account/{accountNumber}/balance`
        
        응답 예시
        
        ```json
        {
        	"bankName" : "우체국",
        	"accountNumber": 40008502659998,
        	"owner": "고건",
        	"balance": 50000000
        }
        ```
        
    - 계좌 개설
        
        `POST - /api/v1/account/create`
        
        ```json
        {
        	"bank": "우체국",
        	"accountNumber": 40008502659998,
        	"type": "deposit",
        	"owner": "고건",
        	"interestRate" : 2.0,
        	"expirationDate": "2025-01-23"
        }
        ```
        
    - 입금
        
        `POST - /api/v1/account/deposit`
        
        ```json
        {
        	"accountNumber": 40008502659998,
        	"amount": 10000000
        }
        ```
        
    - 출금
        
        `POST - /api/v1/account/withdraw`
        
        ```json
        {
        	"accountNumber": 40008502659998,
        	"amount": 10000000
        }
        ```
        
    - 송금
        
        `POST - /api/v1/account/transfer`
        
        ```json
        {
        	"accountNumber": 40008502659998,
        	"toAccountNumber": 123456789,
        	"amount": 10000000
        }
        ```
        

- Product
    - 내 상품 정보 조회
        
        `GET - /api/v1/product/{accountNumber}`
        
        응답 예시

      ```json
      {
        "productName": "installment",
        "interestRate" : 2.0,
        "expirationDate": "2025-01-23"
      }
      ```
