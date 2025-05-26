package com.devsoncall.accounts.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsoncall.accounts.dto.ResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount() {
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto("200", "Account created!"));
	}
}
