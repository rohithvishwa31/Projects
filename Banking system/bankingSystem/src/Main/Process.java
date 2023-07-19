package Main;

import java.sql.SQLException;
import java.util.Scanner;

import DTHRecharge.DTHRecharge;
import metroRecharge.metroRecharge;
import mobileRecharge.mobileRecharge;

public class Process{
	Scanner get = new Scanner(System.in);
	String phoneNumber, Username, Password, Account_no, Bank, Account_type, Account_holder;
	
	public Process(String phoneNumber,String Username,String Password,String Account_no,String Bank,String Account_type,String Account_holder){
		this.phoneNumber = phoneNumber;
		this.Username = Username;
		this.Password = Password;
		this.Account_no = Account_no;
		this.Account_holder = Account_holder;
		this.Bank = phoneNumber;
		this.Account_type = phoneNumber;
	}
	public void process() throws SQLException {
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\tHOME ");
		System.out.println("-------------------------------------------------------------------------------------\n\n");
		System.out.println("WHAT WOULD YOU LIKE TO DO ? \n");
		System.out.println("ENTER 1 - MOBILE RECHARGE");
		System.out.println("ENTER 2 - DTH/CABLE RECHARGE");
		System.out.println("ENTER 3 - METRO RECHARGE");
		System.out.println("ENTER 4 - Quit");
		mobileRecharge m1 = new mobileRecharge(phoneNumber, Username, Password, Account_no, Bank, Account_type, Account_holder);
		DTHRecharge d1 = new DTHRecharge(Account_no, Bank, Account_holder);
		metroRecharge me1 = new metroRecharge(Account_no, Bank, Account_holder);
		while(true) {
			System.out.print("ENTER YOUR CHOICE : ");
			int option = get.nextInt();
			if(option == 1) {
				m1.RechargeProcess();
			}else if(option == 2) {
				d1.DTHRechargeProcess();
			}else if(option == 3) {
				me1.metroRechargeProcess();
			}else if(option == 4){
				System.exit(0);
			}else {
				System.out.print("ENTER A VALID CHOICE : ");
			}
		}
	}
}