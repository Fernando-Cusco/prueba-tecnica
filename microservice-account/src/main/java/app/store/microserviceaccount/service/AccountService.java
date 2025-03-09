package app.store.microserviceaccount.service;

import app.store.microserviceaccount.domain.entity.AccountEntity;
import app.store.microserviceaccount.dto.AccountDto;
import app.store.microserviceaccount.dto.ResponseDto;

public interface AccountService {
    ResponseDto saveAccount(AccountDto accountDto);
    void saveDefaultAccount(Long clientId);
    ResponseDto getAccountById(Long accountId);
    ResponseDto updateAccount(Long accountId, AccountDto accountDto);
    ResponseDto deleteAccount(Long accountId);
    ResponseDto getAllAccounts(Long clientId);

    AccountEntity getAccountEntityById(Long id);

    AccountEntity saveOrUpdateAccount(AccountEntity accountEntity);


}
