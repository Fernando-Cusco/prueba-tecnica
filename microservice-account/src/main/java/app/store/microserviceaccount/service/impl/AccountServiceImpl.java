package app.store.microserviceaccount.service.impl;

import app.store.microserviceaccount.client.ClientService;
import app.store.microserviceaccount.domain.entity.AccountEntity;
import app.store.microserviceaccount.domain.repository.AccountRepository;
import app.store.microserviceaccount.dto.AccountDto;
import app.store.microserviceaccount.dto.ResponseDto;
import app.store.microserviceaccount.mapper.AccountMapper;
import app.store.microserviceaccount.service.AccountService;
import app.store.microserviceaccount.service.common.ServiceCommon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl extends ServiceCommon implements AccountService {


    private static final String CLASS_NAME = AccountServiceImpl.class.getSimpleName();

    private final AccountRepository accountRepository;

    private static final Random RANDOM = new Random();

    private final ClientService clientService;

    @Override
    public ResponseDto saveAccount(AccountDto accountDto) {
        log.info("{}.saveAccount() method is called", CLASS_NAME);
        String clientName = clientService.getDataClientById(accountDto.getClientId());
        if (clientName == null) {
            return generateResponseNotFound("Client not found");
        }
        AccountEntity accountEntity = AccountMapper.toEntity(accountDto);
        accountEntity.setClientName(clientName);
        accountRepository.save(accountEntity);
        accountDto = AccountMapper.toDto(accountEntity);
        return generateResponseSuccessCreated("Account saved successfully", accountDto);
    }

    @Override
    public void saveDefaultAccount(Long clientId) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setClientId(clientId);
        accountEntity.setAmount(BigDecimal.ZERO);
        accountEntity.setStatus(true);
        accountEntity.setAccountType("AHORROS");
        String accountNumber =  RANDOM.nextInt(1000000000) + "";
        accountEntity.setAccountNumber(accountNumber);
        accountRepository.save(accountEntity);
        log.info("{}.saveDefaultAccount() method is called", CLASS_NAME);
        log.info("Default account created for client: {}, {}", clientId, accountEntity);
    }

    @Override
    public ResponseDto getAccountById(Long accountId) {
        log.info("{}.getAccountById() method is called", CLASS_NAME);
        AccountEntity accountEntity = accountRepository.findById(accountId).orElse(null);
        if (accountEntity == null) {
            return generateResponseNotFound("Account not found");
        }
        AccountDto accountDto = AccountMapper.toDto(accountEntity);
        return generateResponseSuccess("Account retrieved successfully", accountDto);
    }

    @Override
    public ResponseDto updateAccount(Long accountId, AccountDto accountDto) {
        return null;
    }

    @Override
    public ResponseDto deleteAccount(Long accountId) {
        return null;
    }

    @Override
    public ResponseDto getAllAccounts(Long clientId) {
        List<AccountEntity> accountEntities = accountRepository.findAllByClientId(clientId);
        if (accountEntities.isEmpty()) {
            return generateResponseNotFound("No accounts found for client");
        }
        List<AccountDto> accounts = accountEntities.stream().map(AccountMapper::toDto).toList();
        return generateResponseSuccess("Accounts retrieved successfully", accounts);
    }

    @Override
    public AccountEntity getAccountEntityById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public AccountEntity saveOrUpdateAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }
}
