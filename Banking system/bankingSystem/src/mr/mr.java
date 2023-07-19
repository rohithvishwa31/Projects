package mr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


class SQLCommands{
	void sql(String Username, String Password) throws SQLException{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		
		Statement stmt = con.createStatement();
		String s = "Select * FROM Credentials where Username=\"" + Username + "\" and Password=\"" + Password+ "\";";
		ResultSet rs=stmt.executeQuery(s);
		if(!rs.next()) {
			System.out.println("Wrong Username and Password."); 

		}else {
			System.out.println("Correct Username and Password.");
		}
	}
	
}
class Mobiledetails{
	String phoneNumber, provider;
	void setPhNumber(String no) {
	
		this.phoneNumber = no;
	}
	String getNumber() {
		return phoneNumber;
	}	
	
	void setProvider(String provider) {
		this.provider = provider;
	}
	
	String getProvider() {
		return provider;
	}
}


public class mr {

	Scanner get = new Scanner(System.in);
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
	
	void plans(String Provider) {
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
		    	System.out.println("₹239 - 28 DAYS | 1.5GB/DAY | UNLIMITED CALLS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹299 - 28 DAYS | 2GB/DAY | UNLIMITED CALLS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹719 - 84 DAYS | 2GB/DAY | UNLIMITED CALLS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹666 - 84 DAYS | 1.5GB/DAY | UNLIMITED CALLS | MORE DETAILS REFER - " + Provider + " PLANS");  	
		    	break;
		    }else if(option == 2) {
		    	System.out.println("DATA ADD-ON PLANS");
		    	System.out.println("₹301 - 30 DAYS | 50GB | VOICE : NA | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹181 - 30 DAYS | 30GB | VOICE : NA | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹121 - BASE PLAN | 12GB | ONLY FOR USERS WITH VALID PLANS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹61  - BASE PLAN | 6GB | ONLY FOR USERS WITH VALID PLANS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹15  - BASE PLAN | 1GB | ONLY FOR USERS WITH VALID PLANS | MORE DETAILS REFER - " + Provider + " PLANS");
		    	break;
		    }else if(option == 4) {
		    	System.out.println("ISD/ROAMING PLANS");
		    	System.out.println("₹501  - 28 DAYS | TALKTIME RS. 424 | 5 ISD SMS  | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹1599 - 15 DAYS | TALKTIME 150 MINS | ONLY FOR QATAR, SAUDI ARABIA, UAE  | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹3000 - 30 DAYS | TALKTIME 300 MINS | ONLY FOR QATAR, SAUDI ARABIA, UAE  | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹6799 - 30 DAYS | TALKTIME 500 MINS | ONLY FOR QATAR, SAUDI ARABIA, UAE  | MORE DETAILS REFER - " + Provider + " PLANS");
		    	break;
		    	
		    }else if(option == 3) {
		    	System.out.println("TOP UP PLANS");
		    	System.out.println("₹1000 - VALIDITY NA | TALKTIME ₹844.46 | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹20   - VALIDITY NA | TALKTIME ₹14.95 | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹100  - VALIDITY NA | TALKTIME ₹81.75 | MORE DETAILS REFER - " + Provider + " PLANS");
		    	System.out.println("₹50   - VALIDITY NA | TALKTIME ₹39.37 | MORE DETAILS REFER - " + Provider + " PLANS");
		    	break;
		    }else {
		    	System.out.println("ENTER A VALID OPTION : ");
		    }
		}
		
	    
	}
	    

	
	void RechargeProcess() throws SQLException {
		Mobiledetails num1 = new Mobiledetails();
		SQLCommands query1 = new SQLCommands();
		System.out.println("Enter username : ");
		String Username = get.next();
		System.out.println("Enter password : ");
		String Password = get.next();
		query1.sql(Username, Password);
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
