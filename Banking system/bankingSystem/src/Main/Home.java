package Main;
import mobileRecharge.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import DTHRecharge.DTHRecharge;
import metroRecharge.metroRecharge;

class SQLCommands{
	ResultSet AccountSearch(String phonenumber) throws SQLException{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String s = "Select Account_no, Bank, Account_type FROM Bank_Account where phone_no=\"" + phonenumber + "\";" ;
		ResultSet rs=stmt.executeQuery(s);
		return rs;
	}
	
	void addUser(String name, String PhoneNumber, String Password, String Account_no) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		
		Statement stmt = con.createStatement();
		String s = "INSERT INTO App_users VALUES ('" + name + "', '" + PhoneNumber + "', '" + Password + "', '" + Account_no + "')";
		stmt.executeUpdate(s);
	}
	
	boolean checkUser(String Username, String Password) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String s = "Select * FROM app_users where PhoneNumber='" + Username + "' and Password='" + Password+ "';";
		
		ResultSet rs = stmt.executeQuery(s);
		if(!rs.next()) {
			return false;
		}else {
			return true;
		}
		
	}
	
	ResultSet getAccountDetails(String phoneNumber) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String s = "SELECT * FROM App_users WHERE PhoneNumber = '" + phoneNumber + "';";
		
		ResultSet Account = stmt.executeQuery(s);
		return Account;
	}
	
	ResultSet getBankDetails(String phoneNumber) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String s = "SELECT * FROM Bank_account WHERE phone_no = '" + phoneNumber + "';";
		
		ResultSet Account = stmt.executeQuery(s);
		return Account;
	}
	
	ResultSet getAccountNo(String phoneNumber) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String s = "SELECT * FROM Bank_account WHERE Account_no = (SELECT Account_no FROM App_users WHERE PhoneNumber = '" + phoneNumber + "');";
		ResultSet Account = stmt.executeQuery(s);
		return Account;
	}
}

class userSignin{
	String phoneNumber, Username, Password, Account_no, Bank, Account_type, Account_holder;
	SQLCommands query = new SQLCommands();
	ResultSet Account, BankDetails, ExistDetails;
	void getDetails(String phoneNumber) throws SQLException {
		this.Account = query.getAccountDetails(phoneNumber);
		this.BankDetails = query.getBankDetails(phoneNumber);
		this.ExistDetails = query.getAccountNo(phoneNumber);
	}	
	
	void setValues() throws SQLException {
		while(BankDetails.next()) {
			this.Account_no = BankDetails.getString("Account_no");
			this.Account_type = BankDetails.getString("Account_type");
			this.Bank = BankDetails.getString("Bank");
			this.Account_holder = BankDetails.getString("Account_holder");
		}
		while(Account.next()) {
			this.Username = Account.getString("Name");
			this.phoneNumber = Account.getString("PhoneNumber");
		}
		
	}
	
	void setExistingValues(String phoneNumber) throws SQLException {
		ExistDetails.beforeFirst();
		while(ExistDetails.next()) {
			this.Account_no = ExistDetails.getString("Account_no");
			this.Account_type = ExistDetails.getString("Account_type");
			this.Bank = ExistDetails.getString("Bank");
			this.Account_holder = ExistDetails.getString("Account_holder");
		}
		while(Account.next()) {
			this.Username = Account.getString("Name");
			this.phoneNumber = Account.getString("PhoneNumber");
		}
	}
	
	void displayAccountDetails() {
		System.out.println("\n");
		System.out.println("YOUR ACCOUNT DETAILS : ");
		System.out.println("ACCOUNT NO   - " + this.Account_no);
		System.out.println("BANK         - " + this.Bank);
		System.out.println("ACCOUNT TYPE - " + this.Account_type);
	}
	
	String[] getValues() {
		String[] values= new String[7];
		values[0] = this.phoneNumber;
		values[1] = this.Username;
		values[2] = this.Password;
		values[3] = this.Account_no;
		values[4] = this.Bank;
		values[5] = this.Account_type;
		values[6] = this.Account_holder;
		return values;
	}
}

class SignUp extends userSignin{
	String phoneNumber, name, Account_no, Bank, Account_type, Password;
	ResultSet bankDetails;
	SQLCommands query1 = new SQLCommands();
	Scanner get = new Scanner(System.in);
	void getInput() throws SQLException {
		System.out.println("ENTER NAME : ");
		this.name = get.next();
		System.out.println("ENTER PHONE NUMBER : ");
		phoneNumber = get.next();     
		System.out.println("\n");
		
	}
	
	void getBank() throws SQLException {
		ResultSet rs = query1.AccountSearch(phoneNumber);
		int i;
		if(!rs.next()) {
			System.out.println("NO ACCOUNT LINKED");
		}else {		
			rs.beforeFirst();
			System.out.println("BANK ACCOUNTS LINKED WITH THIS ACCOUNT : ");
			while(rs.next()) {
				i = rs.getRow();
				String Account_no = rs.getString("Account_no");
				String Bank = rs.getString("Bank");
				String Account_type = rs.getString("Account_type");	
				System.out.println("ENTER " +i +" - " + Account_no +"-"+ Bank +"-"+ Account_type);
			}
			boolean flag = false;
			System.out.println("SELECT AN ACCOUNT");
			while(true) {
				int option = get.nextInt();
				while(rs.absolute(option)) {
					this.Account_no = rs.getString("Account_no");
					this.Account_type = rs.getString("Account_type");
					this.Bank = rs.getString("Bank");
					flag  = true;
					break;
				}
				if(flag == true) {
					break;
				}else {
					System.out.println("Enter a valid option : ");
				}
	
			}	
			System.out.print("ENTER A STRONG PASSWORD FOR YOUR ACCOUNT : ");
			String cpass;
			while(true) {
				Password = get.next();
				System.out.print("CONFIRM PASSWORD : ");
				cpass = get.next();
				if(cpass != Password) {
					System.out.println("PASSWORD CONFIRMED");
					break;
				}else {
					System.out.print("PASSWORD DID NOT MATCH, RE-ENTER : ");
					
				}
			}
			
			System.out.println("ACCOUNT ADDED SUCESSFULLY!!\n");
			System.out.println("YOUR ACCOUNT DETAILS : ");
			System.out.println("ACCOUNT NO   - " + Account_no);
			System.out.println("BANK         - " + Bank);
			System.out.println("ACCOUNT TYPE - " + Account_type);
			
			query1.addUser(name, phoneNumber, Password, Account_no);
		}
		super.phoneNumber = phoneNumber;
		super.getDetails(phoneNumber);
	}
	
	
}


class Login extends userSignin{
	Scanner get = new Scanner(System.in);
	SQLCommands query = new SQLCommands();
	String phonenumber;
	String password;
	boolean exist;
	void credentialCheck() throws SQLException {
		
		while(true) {
			System.out.print("ENTER YOUR PHONE NUMBER : ");
			this.phonenumber = get.next();
			System.out.print("ENTER YOUR PASSWORD : ");
			this.password = get.next();
			boolean exist = query.checkUser(phonenumber, password);
			if(exist == true) {
				super.phoneNumber = phonenumber;
				super.getDetails(this.phonenumber);
				super.setExistingValues(this.phonenumber);
				super.displayAccountDetails();
				break;
			}else {
				System.out.println("\nCREDENTIALS INCORRECT");
				System.out.println("ENTER 1 - Re-Enter details");
				System.out.println("ENTER 2 - ADD A ACCOUNT");
				int option = get.nextInt();
				if(option == 1) {
					continue;
				}else if(option == 2) {
					Home.main(null);
				}
			}
		}
		
	}
	
	
	
}



public class Home {
	static Scanner get = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		String phoneNumber = null, Username = null, Password = null, Account_no = null, Bank = null, Account_type = null, Account_holder = null;
		
		System.out.println("\n\n-------------------------------------------------------------------------------------");
		System.out.println("\t\t\tWELCOME TO PAYPOINT ");
		System.out.println("-------------------------------------------------------------------------------------\n\n");
		
		System.out.println("\tEnter 1 - LOGIN (EXISTING USER)");
		System.out.println("\tEnter 2 - SIGN UP (NEW USER)\n");
		System.out.print("\tEnter your choice : ");
		int option = get.nextInt();
		if(option == 2) {
			SignUp s1 = new SignUp();
			s1.getInput();
			s1.getBank();
			String[] values = s1.getValues();
			phoneNumber = values[0];
			Username = values[1];
			Password = values[2];
			Account_no = values[3];
			Bank = values[4];
			Account_type = values[5];
			Account_holder = values[6];
			Home.main(null);
		}else if (option == 1){
			System.out.println("-------------------------------------------------------------------------------------\n\n");
			Login l1 = new Login();
			l1.credentialCheck();
			String[] values = l1.getValues();
			phoneNumber = values[0];
			Username = values[1];
			Password = values[2];
			Account_no = values[3];
			Bank = values[4];
			Account_type = values[5];
			Account_holder = values[6];
		}
		Process p = new Process(phoneNumber, Username, Password, Account_no, Bank, Account_type, Account_holder);
		p.process();
	}
}