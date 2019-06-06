package com.qa.MapTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	private JSONUtil jsonUtil;
	private AccountMapRepository amr;
	private Account acc1;
	private Account acc2;
	private String acc1JSON = "{\"id\":1,\"accountNumber\":1234,\"firstName\":\"John\",\"lastName\":\"Smith\"}";
	private String acc2JSON = "{\"id\":2,\"accountNumber\":12345,\"firstName\":\"Jane\",\"lastName\":\"Doe\"}";

	@Before
	public void setup() {
		jsonUtil = new JSONUtil();
		amr = new AccountMapRepository();
		acc1 = new Account(1, 1234, "John", "Smith");
		acc2 = new Account(2, 12345, "Jane", "Doe");
	}

	@Test
	public void addAccountTest() {

		amr.createAccount(acc1JSON);

		assertEquals(1, amr.getAccountMap().size());
		assertEquals("John", amr.getAccountMap().get(1234).getFirstName());

	}

	@Test
	public void add2AccountsTest() {

		amr.createAccount(acc1JSON);
		amr.createAccount(acc2JSON);

		assertEquals(2, amr.getAccountMap().size());
		assertEquals("John", amr.getAccountMap().get(1234).getFirstName());
		assertEquals("Jane", amr.getAccountMap().get(12345).getFirstName());
	}

	@Test
	public void removeAccountTest() {

		amr.getAccountMap().put(1234, acc1);

		amr.deleteAccount(1234);

		assertEquals(0, amr.getAccountMap().size());
	}

	@Test
	public void remove2AccountsTest() {

		amr.getAccountMap().put(1234, acc1);
		amr.getAccountMap().put(12345, acc2);
		amr.deleteAccount(1234);
		amr.deleteAccount(12345);

		assertEquals(0, amr.getAccountMap().size());
	}

	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		amr.getAccountMap().put(1234, acc1);
		amr.getAccountMap().put(12345, acc2);

		amr.deleteAccount(1234);
		amr.deleteAccount(1);

		assertEquals(1, amr.getAccountMap().size());
		assertEquals("Jane", amr.getAccountMap().get(12345).getFirstName());

	}

	@Test
	public void jsonStringToAccountConversionTest() {
		Account acc = jsonUtil.getObjectForJSON(acc1JSON, Account.class);
		assertEquals(1234, acc.getAccountNumber());
	}

	@Test
	public void accountConversionToJSONTest() {
		assertEquals(acc1JSON, jsonUtil.getJSONForObject(acc1));
	}

	@Test
	public void returnAccountsWhenMapEmpty() {
		assertEquals("{}", amr.getAllAccounts());
	}

	@Test
	public void returnAccountsWhenMapFilled() {
		amr.getAccountMap().put(1234, acc1);
		assertEquals(1, amr.getAccountMap().size());
		assertEquals("{\"1234\":{\"id\":1,\"accountNumber\":1234,\"firstName\":\"John\",\"lastName\":\"Smith\"}}",
				amr.getAllAccounts());
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		assertEquals(0, amr.findAccountsWithFirstName("John"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		amr.getAccountMap().put(1234, acc1);

		assertEquals(1, amr.findAccountsWithFirstName("John"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		Account acc3 = new Account(3, 12, "John", "Doe");
		amr.getAccountMap().put(1234, acc1);
		amr.getAccountMap().put(12, acc3);

		assertEquals(2, amr.findAccountsWithFirstName("John"));
	}

}
