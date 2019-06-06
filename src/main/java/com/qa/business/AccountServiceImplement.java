package com.qa.business;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.qa.persistence.repository.AccountRepository;

@Default
public class AccountServiceImplement implements AccountService {

	@Inject
	private AccountRepository repo;

	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String movie) {

		return "Can't Add This Account";
	}

	@Override
	public String deleteAccount(int id) {
		return repo.deleteAccount(id);
	}

	@Override
	public String updateAccount(int accountNumber, String account) {
		return repo.updateAccount(accountNumber, account);
	}

	@Override
	public String getAnAccount(int id) {
		return null;
	}

}
