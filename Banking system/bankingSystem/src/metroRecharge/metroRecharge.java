
package metroRecharge;

import java.sql.SQLException;
import java.util.Scanner;

import Payment.Payment;
import mobileRecharge.Mobiledetails;

public class metroRecharge {
	Scanner get = new Scanner(System.in);
	Payment pay = new Payment();
	String subscriberId, Account_no, Bank, Account_holder;
	public metroRecharge(String Account_no, String Bank, String Account_holder) {
		this.Account_no = Account_no;
		this.Account_holder = Account_holder;
		this.Bank = Bank;
	}
	public boolean validCheck(String metroID) {
		
		boolean val = true;
		try{
			long x = Long.parseLong(metroID);	
			val = true;
		}catch(Exception f){
			val = false;
		}
		if (metroID.length()==8 && val == true){
			return true;
		}else{
			return false;
		}
		
	}
	
	public String getID() {
		String ID;
		ID = get.next();
		if(validCheck(ID) == true) {
			return ID;
		}else {
			System.out.println("RE-ENTER A VALID ID : ");
			ID = getID();
		}
		return ID;
	}
	
	String getBiller(metroDetails obj) {
		System.out.println("SELECT YOUR METRO : ");
	    System.out.println("ENTER '1' - BENGALURU METRO");
	    System.out.println("ENTER '2' - CHENNAI METRO");
	    System.out.println("ENTER '3' - DELHI METRO");
	    System.out.println("ENTER '4' - MUMBAI METRO");
	    System.out.println("ENTER '5' - HYDERABAD METRO");
	    String metro = null;
	    while(true) {
	    	int option= get.nextInt();
		    if(option == 1) {
		    	obj.setMetro("BENGALURU METRO");
	    		metro =  "BENGALURU METRO";
	    		break;
		    }else if(option == 2) {
		    	obj.setMetro("CHENNAI METRO");
	    		metro =  "CHENNAI METRO";
	    		break;
		    }else if(option == 3) {
		    	obj.setMetro("DELHI METRO");
	    		metro =  "DELHI METRO";
	    		break;
		    }else if(option == 4) {
		    	obj.setMetro("MUMBAI METRO");
	    		metro =  "MUMBAI METRO";
	    		break;
		    }else if(option == 5) {
		    	obj.setMetro("HYDERABAD METRO");
	    		metro =  "HYDERABAD METRO";
	    		break;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }  
	    }
		return metro;
		
	}
	
	void payment(String operator) throws SQLException {
		System.out.println("YOU'VE CHOSEN "+ operator);
		System.out.println("ENTER AMOUNT TO RECHARGE : ");
		int amount = get.nextInt();
	    while(true) {
	    	System.out.println("ENTER 1 TO MAKE PAYMENT OR ENTER 2 TO RE-ENTER AMOUNT");
		    int option = get.nextInt();
		    if(option == 1) {
		    	break;
		    }else if(option == 2){
		    	System.out.println("ENTER AMOUNT: ");
		    	amount = get.nextInt();
		    	continue;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }
	    }
	    pay.pay(amount, subscriberId, Account_no, Bank, Account_holder);
	}
	
	public void metroRechargeProcess() throws SQLException {
		metroDetails metroR = new metroDetails();
	    System.out.println("YOU'VE ENTERED INTO METRO SMART CARD RECHARGE PORTAL");
	    String ID, metro;
	    System.out.println("ENTER SMART CARD ID: ");
    	ID = getID();
	    while(true) {
	    	System.out.println("ARE YOU SURE TO CONTINUE WITH THIS ID = " + ID);
		    System.out.println("ENTER 1 TO CONTINUE OR ENTER 2 TO RE-ENTER");
		    int option = get.nextInt();
		    if(option == 1) {
		    	break;
		    }else if(option == 2){
		    	System.out.println("ENTER SMART CARD ID : ");
		    	ID = getID();
		    	break;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }
	    }
	    metro = getBiller(metroR);
	    metroR.setID(ID);
	    payment(metroR.getMetro());
	}
}
