package com.devsoncall.accounts.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.devsoncall.accounts.constants.AccountsConstants;
import com.devsoncall.accounts.dto.AccountsDto;
import com.devsoncall.accounts.dto.CustomerDto;
import com.devsoncall.accounts.entity.Accounts;
import com.devsoncall.accounts.entity.Customer;
import com.devsoncall.accounts.exceptions.CustomerExistsException;
import com.devsoncall.accounts.exceptions.ResourceNotFoundException;
import com.devsoncall.accounts.mapper.AccountsMapper;
import com.devsoncall.accounts.mapper.CustomerMapper;
import com.devsoncall.accounts.repository.AccountsRepository;
import com.devsoncall.accounts.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private AccountsRepository accountsRepo;
  private CustomerRepository customerRepo;

  /** @param customerDto - CustomerDto Object */
  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer =
        customerRepo.findByMobileNumber(customer.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerExistsException(
          "Customer account already exists with phone number "
              + customerDto.getMobileNumber()
              + ", please try with different number.");
    }
    Customer savedCustomer = customerRepo.save(customer);
    accountsRepo.save(createNewAccount(savedCustomer));
  }

  /**
   * @param customer - Customer Object
   * @return the new account details
   */
  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);
    return newAccount;
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return Accounts Details based on a given mobileNumber
   */
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer = customerRepo.findByMobileNumber(mobileNumber)
    		.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
    );
    Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId())
    		.orElseThrow(()-> new ResourceNotFoundException("Accounts", "CustomerID", customer.getCustomerId().toString())
    );
    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
    customerDto.setAccountsDto(accountsDto);
    return customerDto;
  }

  /**
   * @param customerDto - CustomerDto Object
   * @return boolean indicating if the update of Account details is successful or not
   */
  public boolean updateAccount(CustomerDto customerDto) {
	  boolean isUpdated = false;
	  AccountsDto accountsDto = customerDto.getAccountsDto();
	  if(accountsDto !=null ){ 
		  // find account
		  Accounts accounts = accountsRepo.findById(accountsDto.getAccountNumber())
				  .orElseThrow(()-> new ResourceNotFoundException("Accounts", "AccountNumber",accountsDto.getAccountNumber().toString())
		  );
		  AccountsMapper.mapToAccountsDto(accounts, accountsDto); // update account
		  accountsRepo.save(accounts);
		  // find customer
		  Customer customer = customerRepo.findByMobileNumber(customerDto.getMobileNumber())
				  .orElseThrow(()-> new ResourceNotFoundException("Customer", "MobileNumber",customerDto.getMobileNumber().toString())
		  );
		  CustomerMapper.mapToCustomerDto(customer, customerDto); // update customer
		  customerRepo.save(customer);
		  isUpdated = true;
	  }
    return isUpdated;
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return boolean indicating if the delete of Account details is successful or not
   */
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepo.findByMobileNumber(mobileNumber)
    		.orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
    );
    customerRepo.deleteById(customer.getCustomerId());
    accountsRepo.deleteById(customer.getCustomerId());
    return true;
  }
}
