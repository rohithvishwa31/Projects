package mobileRecharge;

import java.util.Scanner;

import Main.Home;

import java.sql.*;
import Payment.*;


public class mobileRecharge {
	String phoneNumber, Username, Password, Account_no, Bank, Account_type, Account_holder;
	Scanner get = new Scanner(System.in);
	Payment pay = new Payment();
	Home values = new Home();
	public mobileRecharge(String phoneNumber,String Username,String Password,String Account_no,String Bank,String Account_type,String Account_holder){
		this.phoneNumber = phoneNumber;
		this.Username = Username;
		this.Password = Password;
		this.Account_no = Account_no;
		this.Account_holder = Account_holder;
		this.Bank = Bank;
		this.Account_type = Account_type;
	}
	public boolean validCheck(String no) {
		boolean val = true;
		try{
			long x = Long.parseLong(no);	
		}catch(Exception f){
			val = false;
		}
		if (no.length() == 10 && (no.charAt(0)=='9' || no.charAt(0)=='8' || no.charAt(0)=='7' || no.charAt(0)=='6') && val == true){
			return true;
		}else{
			return false;
		}
	}
	
	
	public String getNo()
	{	
		String no;
        no = get.next();     
        if(validCheck(no) == true){
        	return no;
        }else{
            System.out.println("RE-ENTER A VALID NUMBER : ");
            no = getNo();
        }
		return no;
	}
	
	String getProvider(Mobiledetails obj) {
		
		System.out.println("SELECT NETWORK OPERATOR : ");
	    System.out.println("ENTER '1' - JIO PREPAID");
	    System.out.println("ENTER '2' - AIRTEL PREPAID");
	    System.out.println("ENTER '3' - BSNL TELECOM");
	    System.out.println("ENTER '4' - VI PREPAID");
	    String provider = null;
	    while(true) {
	    	int option= get.nextInt();
		    if(option == 1) {
		    	obj.setProvider("JIO PREPAID");
	    		provider =  "JIO PREPAID";
	    		break;
		    }else if(option == 2) {
		    	obj.setProvider("AIRTEL PREPAID");
	    		provider =  "AIRTEL PREPAID";
	    		break;
		    }else if(option == 3) {
		    	obj.setProvider("BSNL TELECOM");
	    		provider =  "BSNL TELECOM";
	    		break;
		    }else if(option == 4) {
		    	obj.setProvider("VI PREPAID");
	    		provider =  "VI PREPAID";
	    		break;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }  
	    }
	    return provider;    
	}
	
	void plans(String Provider) throws SQLException {
		System.out.println("YOU'VE CHOOSEN - " + Provider );
		System.out.println("SELECT PLAN CATEGORY: ");
		System.out.println("ENTER '1' - SMART PHONE");
	    System.out.println("ENTER '2' - DATA ADD-ON");
	    System.out.println("ENTER '3' - TOP UP");
	    System.out.println("ENTER '4' - ISD/ROAMING");
		while(true) {
			int option = get.nextInt();
		    if(option == 1) {
		    	System.out.println("SMART PHONE PLANS");
		    	System.out.println("ENTER 1 - ₹239 - 28 DAYS | 1.5GB/DAY | UNLIMITED CALLS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 2 - ₹299 - 28 DAYS | 2GB/DAY | UNLIMITED CALLS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 3 - ₹719 - 84 DAYS | 2GB/DAY | UNLIMITED CALLS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 4 - ₹666 - 84 DAYS | 1.5GB/DAY | UNLIMITED CALLS | MORE DETAILS REFER - " + Provider + " PLANS");  
		    	System.out.println("ENTER A VALID OPTION :");
		    	while(true) {
		    		int option1 = get.nextInt();
			    	if(option1 == 1) {
			    		int amount = 239;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 2) {
			    		int amount = 299;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 3) {
			    		int amount = 719;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 4) {
			    		int amount = 666;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else {
			    		System.out.println("ENTER A VALID OPTION : ");
			    		continue;
			    	}
		    	}
		    	break;
		    }else if(option == 2) {
		    	System.out.println("DATA ADD-ON PLANS");
		    	System.out.println("ENTER 1 - ₹301 - 30 DAYS | 50GB | VOICE : NA | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 2 - ₹181 - 30 DAYS | 30GB | VOICE : NA | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 3 - ₹121 - BASE PLAN | 12GB | ONLY FOR USERS WITH VALID PLANS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 4 - ₹61  - BASE PLAN | 6GB | ONLY FOR USERS WITH VALID PLANS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 5 - ₹15  - BASE PLAN | 1GB | ONLY FOR USERS WITH VALID PLANS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER A VALID OPTION :");
		    	while(true) {
		    		int option1 = get.nextInt();
			    	if(option1 == 1) {
			    		int amount = 301;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 2) {
			    		int amount = 181;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 3) {
			    		int amount = 121;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 4) {
			    		int amount = 61;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 5) {
			    		int amount = 15;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else {
			    		System.out.println("ENTER A VALID OPTION : ");
			    		continue;
			    	}
		    	}
		    	
		    	break;
		    }else if(option == 4) {
		    	System.out.println("ISD/ROAMING PLANS");
		    	System.out.println("ENTER 1 - ₹501  - 28 DAYS | TALKTIME RS. 424 | 5 ISD SMS  | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 2 - ₹1599 - 15 DAYS | TALKTIME 150 MINS | ONLY FOR QATAR, SAUDI ARABIA, UAE  | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 3 - ₹3000 - 30 DAYS | TALKTIME 300 MINS | ONLY FOR QATAR, SAUDI ARABIA, UAE  | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 4 - ₹6799 - 30 DAYS | TALKTIME 500 MINS | ONLY FOR QATAR, SAUDI ARABIA, UAE  | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER A VALID OPTION :");
		    	while(true) {
		    		int option1 = get.nextInt();
			    	if(option1 == 1) {
			    		int amount = 501;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 2) {
			    		int amount = 1599;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 3) {
			    		int amount = 3000;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 4) {
			    		int amount = 6799;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else {
			    		System.out.println("ENTER A VALID OPTION : ");
			    		continue;
			    	}
		    	}
		    	
		    	break;
		    	
		    }else if(option == 3) {
		    	System.out.println("TOP UP PLANS");
		    	System.out.println("ENTER 1 - ₹1000 - VALIDITY NA | TALKTIME ₹844.46 | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 2 - ₹20   - VALIDITY NA | TALKTIME ₹14.95 | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 3 - ₹100  - VALIDITY NA | TALKTIME ₹81.75 | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER 4 - ₹50   - VALIDITY NA | TALKTIME ₹39.37 | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("ENTER A VALID OPTION :");
		    	while(true) {
		    		int option1 = get.nextInt();
			    	if(option1 == 1) {
			    		int amount = 1000;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 2) {
			    		int amount = 20;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 3) {
			    		int amount = 100;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else if(option1 == 4) {
			    		int amount = 50;
			    		pay.pay(amount, phoneNumber, Account_no, Bank, Account_holder);
			    		break;
			    	}else {
			    		System.out.println("ENTER A VALID OPTION : ");
			    		continue;
			    	}
		    	}
		    	break;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }
		}
		
	    
	}
	    

	
	public void RechargeProcess() throws SQLException {
		Mobiledetails num1 = new Mobiledetails();
	    System.out.println("YOU'VE ENTERED INTO RECHARGE PORTAL");
	    String no;
	    System.out.println("ENTER YOUR NO : ");
    	no = getNo();
	    while(true) {
	    	System.out.println("ARE YOU SURE TO CONTINUE WITH THIS NUMBER = +91" + no);
		    System.out.println("ENTER 1 TO CONTINUE OR ENTER 2 TO RE-ENTER");
		    int option = get.nextInt();
		    if(option == 1) {
		    	break;
		    }else if(option == 2){
		    	System.out.println("ENTER MOBILE NUMBER : ");
		    	no = getNo();
		    	continue;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }
	    }
	    num1.setPhNumber(no);
	    getProvider(num1);
	    plans(num1.getProvider());
	}
	
}
