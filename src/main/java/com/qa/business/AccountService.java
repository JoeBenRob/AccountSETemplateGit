package com.qa.business;

public interface AccountService {

	// C
	String addAccount(String account);

	// R
	String getAllAccounts();

	String getAnAccount(int id);

	// U
	String updateAccount(int accountNumber, String id);

	// D
	String deleteAccount(int id);

}
