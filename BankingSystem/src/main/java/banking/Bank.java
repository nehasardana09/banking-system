package banking;

import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts;
	private Long accountNumber = 0L;

	public Bank() {
    // complete the function
    this.accounts = new LinkedHashMap<>();
	}

	private Account getAccount(Long accountNumber) {
		// complete the function
        return this.accounts.getOrDefault(accountNumber, null);
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		// complete the function
		Long accountNumber = generateAccountNumberUnique();
		Account commercialAccount = new CommercialAccount(company, accountNumber, pin, startingDeposit);
		this.accounts.put(accountNumber, commercialAccount);
        return accountNumber;
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		// complete the function
		Long accountNumber = generateAccountNumberUnique();
		Account consumerAccount = new ConsumerAccount(person, accountNumber, pin, startingDeposit);
		this.accounts.put(accountNumber, consumerAccount);
		return accountNumber;
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		// complete the function
		if(!this.accounts.containsKey(accountNumber)) return false;
		Account account = accounts.get(accountNumber);
        return account.validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
		// complete the function
		Account account = this.accounts.get(accountNumber);
		if(account != null){
			return account.getBalance();
		}

        return 0;
	}

	public void credit(Long accountNumber, double amount) {
		Account account = this.accounts.get(accountNumber);
		if(account != null){
			account.creditAccount(amount);
		}
	}

	public boolean debit(Long accountNumber, double amount) {
		Account account = this.accounts.get(accountNumber);
		if(account != null){
			return account.debitAccount(amount);
		}
        return false;
	}

	private Long generateAccountNumberUnique(){
		this.accountNumber = this.accountNumber +1;
		return this.accountNumber;
	}
}
