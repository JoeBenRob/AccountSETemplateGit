package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountMapRepository implements AccountRepository {

	private Map<Integer, Account> accountMap = new HashMap<Integer, Account>();

	@Inject
	private JSONUtil util;

	// You must provide concrete implementation for each of these methods
	// do not change the method signature
	// THINK - if the parameter is a String, or the return type is a String
	// How can I convert to a String from an Object?
	// What utility methods do I have available?

	// You must complete this section using TDD
	// You can use the suggested tests or build your own.

	public String getAllAccounts() {
		return util.getJSONForObject(accountMap);
	}

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public String createAccount(String account) {

		Account acc = util.getObjectForJSON(account, Account.class);

		accountMap.put(acc.getAccountNumber(), acc);

		return "Account added";
	}

	public String deleteAccount(int accountNumber) {
		accountMap.remove(accountNumber);
		return "Account deleted";
	}

	public String updateAccount(int accountNumber, String account) {

		Account acc = util.getObjectForJSON(account, Account.class);

		accountMap.replace(accountNumber, acc);

		return "Account updated";
	}

	public int findAccountsWithFirstName(String name) {
		return (int) accountMap.values().stream().filter(a -> a.getFirstName().equals(name)).count();

	}

}
