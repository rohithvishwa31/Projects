package Payment;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Invoice.Invoice;
import Main.Process;

interface paymentProcess{
	boolean UPIvaliditation() throws SQLException;
	void payProcessing() throws SQLException;
}

class PayProcess implements paymentProcess{
	String phoneNumber, Username, Password, Account_no, Bank, Account_type, Account_holder;
	int amount;
	Scanner get = new Scanner(System.in);
	PayProcess(int amount, String phoneNumber, String Account_no, String Bank, String Account_holder){
		this.amount = amount;
		this.phoneNumber = phoneNumber;
		this.Account_no = Account_no;
		this.Account_holder = Account_holder;
		this.Bank = Bank;
	}
	
	String dateTime() {
		LocalDate myDateObj = LocalDate.now();
		LocalTime myTimeObj = LocalTime.now();
		DateTimeFormatter myFormatTimeObj = DateTimeFormatter.ofPattern("HH:mm:ss");
	    DateTimeFormatter myFormatDateObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    String formattedDate = myDateObj.format(myFormatDateObj) + " at " + myTimeObj.format(myFormatTimeObj);
	    return formattedDate;	
	}
	
	public boolean UPIvaliditation() throws SQLException {
		SQLCommands query = new SQLCommands();
		System.out.print("Enter your UPI PIN : ");
		while(true) {
			int UPI_PIN = get.nextInt();	
			boolean validity = query.PINValidation(Account_no, UPI_PIN);
			if (validity == true) {
				 return true;
			}else {
				System.out.println("ENTER 1 - RE-ENTER");
				System.out.println("ENTER 2 - GO TO HOME");
				int option = get.nextInt();
				if(option == 1) {
					System.out.print("RE-ENTER : ");
				}else {
					return false;
				}
			}
		}
	}
	
	public void payProcessing() throws SQLException {
		SQLCommands query = new SQLCommands();
		boolean upi = UPIvaliditation();
		if(upi == true) {
			String stmt = query.balanceUpdation(this.amount, this.Account_no);
			System.out.print(stmt);
			System.out.print("\n");
			if(stmt=="TRANSACTION SUCCESSFUL!") {
				System.out.println("------------------------------------------------------------");
				System.out.println("\t\tINVOICE");
				System.out.println("------------------------------------------------------------");
				System.out.println("\t₹" + this.amount + " PAID FROM ACCOUNT "+ this.Account_no);
				String time1 = "PAYMENT MADE ON " + dateTime();
				String time = "\tPAYMENT MADE ON " + dateTime();
				System.out.println(time);
				System.out.println("------------------------------------------------------------");
				while(true) {
					
					System.out.println("ENTER 1 - GENERATE INVOICE");
					System.out.println("ENTER 2 - GO HOME");
					int option = get.nextInt();
					if(option == 2) {
						break;
					}else if (option == 1) {
						Invoice file = new Invoice();
						file.createInvoice(this.Account_no, this.Bank, this.amount, time1);
						break;
					}else {
						System.out.print("ENTER A VALID OPTION : ");
					}

				}
				Process p = new Process(phoneNumber, Username, Password, Account_no, Bank, Account_type, Account_holder);
				p.process();
			}else {
				Process p = new Process(phoneNumber, Username, Password, Account_no, Bank, Account_type, Account_holder);
				p.process();
			}
		}else {
			System.out.print("THANK YOU FOR USING OUR PORTAL");
		}
	}
}

public class Payment {
	
	public void pay(int amount, String phoneNumber, String Account_no, String Bank, String Account_holder) throws SQLException {
		PayProcess p1 = new PayProcess(amount, phoneNumber, Account_no, Bank, Account_holder);
		p1.payProcessing();
	}
}
