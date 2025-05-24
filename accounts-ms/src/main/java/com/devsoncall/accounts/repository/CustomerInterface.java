package com.devsoncall.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsoncall.accounts.entity.Customer;

@Repository
public interface CustomerInterface extends JpaRepository<Customer, Long>{
	
}
