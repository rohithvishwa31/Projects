package DTHRecharge;

import java.sql.SQLException;
import java.util.Scanner;

import Payment.Payment;
import mobileRecharge.Mobiledetails;

public class DTHRecharge {
	Scanner get = new Scanner(System.in);
	Payment pay = new Payment();
	String subscriberId, Account_no, Bank, Account_holder;
	public DTHRecharge(String Account_no, String Bank, String Account_holder) {
		this.Account_no = Account_no;
		this.Account_holder = Account_holder;
		this.Bank = Bank;
	}
	public boolean validCheck(String suscriberID) {
		
		boolean val = true;
		try{
			long x = Long.parseLong(suscriberID);	
			val = true;
		}catch(Exception f){
			val = false;
		}
		if (suscriberID.length()==11 && val == true){
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
	
	String getBiller(DTHDetails obj) {
		System.out.println("SELECT DTH/CABLE BILLER : ");
	    System.out.println("ENTER '1' - AIRTEL DIGITAL TV");
	    System.out.println("ENTER '2' - DISH TV");
	    System.out.println("ENTER '3' - D2H");
	    System.out.println("ENTER '4' - TATA SKY");
	    System.out.println("ENTER '5' - SUN DIRECT");
	    String Biller = null;
	    while(true) {
	    	int option= get.nextInt();
		    if(option == 1) {
		    	obj.setBiller("AIRTEL DIGITAL TV");
	    		Biller =  "AIRTEL DIGITAL TV";
	    		break;
		    }else if(option == 2) {
		    	obj.setBiller("DISH TV");
	    		Biller =  "DISH TV";
	    		break;
		    }else if(option == 3) {
		    	obj.setBiller("D2H");
	    		Biller =  "D2H";
	    		break;
		    }else if(option == 4) {
		    	obj.setBiller("TATA SKY");
	    		Biller =  "TATA SKY";
	    		break;
		    }else if(option == 5) {
		    	obj.setBiller("SUN DIRECT");
	    		Biller =  "SUN DIRECT";
	    		break;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }  
	    }
		return Biller;
		
	}
	
	void payment(String provider) throws SQLException {
		System.out.println("YOU'VE CHOSEN "+ provider);
		System.out.println("ENTER AMOUNT TO MAKE PAYMENT : ");
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
	
	public void DTHRechargeProcess() throws SQLException {
		DTHDetails DTH = new DTHDetails();
	    System.out.println("YOU'VE ENTERED INTO DTH RECHARGE PORTAL");
	    String ID, biller;
	    System.out.println("ENTER SUBSCRIBER ID: ");
    	ID = getID();
	    while(true) {
	    	System.out.println("ARE YOU SURE TO CONTINUE WITH THIS ID = " + ID);
		    System.out.println("ENTER 1 TO CONTINUE OR ENTER 2 TO RE-ENTER");
		    int option = get.nextInt();
		    if(option == 1) {
		    	break;
		    }else if(option == 2){
		    	System.out.println("ENTER SUBSCRIBER ID : ");
		    	ID = getID();
		    	break;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }
	    }
	    DTH.setID(ID);
	    getBiller(DTH);
	    payment(DTH.getBiller());
	}
}
