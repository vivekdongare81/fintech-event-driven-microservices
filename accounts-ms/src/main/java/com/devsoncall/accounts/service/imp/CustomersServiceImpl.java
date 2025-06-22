package com.devsoncall.accounts.service.imp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devsoncall.accounts.dto.AccountsDto;
import com.devsoncall.accounts.dto.CardsDto;
import com.devsoncall.accounts.dto.CustomerDetailsDto;
import com.devsoncall.accounts.dto.LoansDto;
import com.devsoncall.accounts.entity.Accounts;
import com.devsoncall.accounts.entity.Customer;
import com.devsoncall.accounts.exceptions.ResourceNotFoundException;
import com.devsoncall.accounts.mapper.AccountsMapper;
import com.devsoncall.accounts.mapper.CustomerMapper;
import com.devsoncall.accounts.repository.AccountsRepository;
import com.devsoncall.accounts.repository.CustomerRepository;
import com.devsoncall.accounts.service.ICustomersService;
import com.devsoncall.accounts.service.client.CardsFeignClient;
import com.devsoncall.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}

