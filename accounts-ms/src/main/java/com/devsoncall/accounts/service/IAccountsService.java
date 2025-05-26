package com.devsoncall.accounts.service;

import com.devsoncall.accounts.dto.CustomerDto;

public interface IAccountsService {
	/*
	 * @Param customerDto - CustomerDto Object
	 */
	void createAccount(CustomerDto customerDto);
}
