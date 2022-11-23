import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
	Map<Integer, Account> data = new TreeMap<Integer, Account>();
	int customerNumber;
	int pinNumber;
	int cst_no;
	public void getLogin() throws IOException {
		boolean end = false;
		this.customerNumber = 0;
		this.pinNumber = 0;
		while (!end) {
			try {
				System.out.print("\nEnter your customer number. Press 0000 to Exit\n");
				customerNumber = menuInput.nextInt();
				if (customerNumber == 0000) {
					mainMenu();
				}
				System.out.print("\nEnter your PIN number: Press 0000 to Exit\n");
				pinNumber = menuInput.nextInt();
				menuInput.nextLine();
				if (pinNumber == 0000) {
					mainMenu();
				}
				Iterator it = data.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					Account acc = (Account) pair.getValue();
					if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
						getAccountType(acc);
						end = true;
						break;
					}
				}
				if (!end) {
					System.out.println("\nWrong Customer Number or Pin Number");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Character(s). Only Numbers.");
			}
		}
	}

	public void getAccountType(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSelect the account you want to access: ");
				System.out.println(" Type 1 - Show All Account Balances");
				System.out.println(" Type 2 - Checking Account");
				System.out.println(" Type 3 - Savings Account");
				System.out.println(" Type 4 - Main Menu");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
					case 1:
						System.out.println("\nChecking Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
						System.out.println("\nSavings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
						break;
					case 2:
						getChecking(acc);
						break;
					case 3:
						getSaving(acc);
						break;
					case 4:
						mainMenu();
						break;
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void getChecking(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nChecking Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Go Back");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
					case 1:
						System.out.println("\nChecking Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
						break;
					case 2:
						acc.getCheckingWithdrawInput();
						break;
					case 3:
						acc.getCheckingDepositInput();
						break;
					case 4:
						acc.getTransferInput("Checking");
						break;
					case 5:
						getAccountType(data.get(customerNumber));
						break;
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void getSaving(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSavings Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Go Back");
				System.out.print("Choice: ");
				int selection = menuInput.nextInt();
				switch (selection) {
					case 1:
						System.out.println("\nSavings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
						break;
					case 2:
						acc.getsavingWithdrawInput();
						break;
					case 3:
						acc.getSavingDepositInput();
						break;
					case 4:
						acc.getTransferInput("Savings");
						break;
					case 5:
						getAccountType(data.get(customerNumber));
						break;
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void createAccount() throws IOException {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nEnter your customer number ");
				this.cst_no = menuInput.nextInt();
				Iterator it = data.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					if (!data.containsKey(this.cst_no)) {
						end = true;
					}
				}
				if (!end) {
					System.out.println("\nThis customer number is already registered");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
		System.out.println("\nEnter PIN to be registered");
		int pin = menuInput.nextInt();
		data.put(this.cst_no, new Account(this.cst_no, pin));
		System.out.println("\nYour new account has been successfully registered!");
		mainMenu();
	}

	public void mainMenu() throws IOException {
		data.put(952141, new Account(952141, 191904, 1000, 5000));
		data.put(123, new Account(123, 123, 20000, 50000));
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\n Type 1 - Login");
				System.out.println(" Type 2 - Create Account");
				System.out.println(" Type 3 - Show All Account Names");
				System.out.println(" Type 4 - Save Account");
				System.out.println(" Type 5 - Load All Accounts");
				System.out.println(" Type 6 - Erase All Saved Accounts");
				System.out.println(" Type 7 - Exit");
				System.out.print("\nChoice: ");
				int choice = menuInput.nextInt();
				switch (choice) {
					case 1:
						getLogin();
						end = true;
						break;
					case 2:
						createAccount();
						end = true;
						break;
					case 3:
						System.out.println(data.keySet());
						break;
					case 4:
						idStorage();
						break;
					case 5:
						accountLoader();
						break;
					case 6:
						System.out.println("ARE YOU SURE? [ 1 : YES ] [ ALL OTHER KEYS : NO ]");
						int response = menuInput.nextInt();
						if(response == 1) {
							clearAccount();
							System.out.println("All of the saved accounts have been cleared");
						} else {
							mainMenu();
						}
					case 7:
						System.out.println("Goodbye!");
						System.exit(1);
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
		System.out.println("\nThank You for using this ATM.\n");
		menuInput.close();
		System.exit(0);
	}

	public void idStorage() throws IOException {
		String line = "";
		Account account;
		StringBuffer buffer = new StringBuffer();
		BufferedWriter save = new BufferedWriter(new FileWriter("/Users/freddy/Projects/ATM-Machine-Java/Account.txt", true));
		BufferedReader reader = new BufferedReader(new FileReader("/Users/freddy/Projects/ATM-Machine-Java/Account.txt"));
		for (int i : data.keySet()) {
			while ((line = reader.readLine()) != null) {
				String[] separate = line.split(",");
				if (i == Integer.parseInt(separate[0])) {
					buffer.replace(0,line.length(), String.format("%s,%s,%f,%f\n", i, separate[1], data.get(i).getCheckingBalance(), data.get(i).getSavingBalance()));
					FileOutputStream fileOut = new FileOutputStream("/Users/freddy/Projects/ATM-Machine-Java/Account.txt");
					fileOut.write(buffer.toString().getBytes());
				} else if (i != Integer.parseInt(separate[0])) {
					account = data.get(i);
					int id = account.getCustomerNumber();
					int pass = account.getPinNumber();
					double check = account.getCheckingBalance();
					double saving = account.getSavingBalance();
					save.write(String.format("%s,%s,%f,%f\n", id, pass, check, saving));
					break;
				}
			}
		}
		System.out.println("Saved");
		save.close();
		}

	public void accountLoader() {
		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/Users/freddy/Projects/ATM-Machine-Java/Account.txt"));
			while((line = reader.readLine()) != null) {
				String[] separate = line.split(",");
				for (int i = 0; i < separate.length/4; i++) {
					int id = Integer.parseInt(separate[0]);
					System.out.print(id + " ");
					int pass = Integer.parseInt(separate[1]);
					System.out.print(pass + " ");
					double check = Double.parseDouble(separate[2]);
					System.out.print(check + " ");
					double saving = Double.parseDouble(separate[3]);
					System.out.print(saving + "\n");
					Account account = new Account(id, pass, check, saving);
					data.put(id, account);
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Loaded");
	}
	public void clearAccount() throws IOException {
		BufferedWriter save = new BufferedWriter(new FileWriter("/Users/freddy/Projects/ATM-Machine-Java/Account.txt"));
		save.write("");
		System.out.println("Account has been wiped out");
		save.close();
	}
}


