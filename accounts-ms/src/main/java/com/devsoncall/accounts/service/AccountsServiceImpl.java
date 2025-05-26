package com.devsoncall.accounts.service;

import com.devsoncall.accounts.dto.CustomerDto;
import com.devsoncall.accounts.repository.AccountsRepository;
import com.devsoncall.accounts.repository.CustomerRepository;

import org.springframework.stereotype.Service;
//
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private AccountsRepository accountsRepo;
	private CustomerRepository customerRepo;

	/*
	 * @Param customerDto - CustomerDto Object
	 */
	@Override
	public void createAccount(CustomerDto customerDto) {

	}

}
