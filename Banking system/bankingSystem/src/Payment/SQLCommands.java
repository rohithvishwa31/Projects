package Payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCommands {
	boolean PINValidation(String Account_no, int UPI_PIN) throws SQLException{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String s = "Select Account_no, UPI_PIN FROM bank_balance WHERE Account_no=" + Account_no + " and UPI_PIN = " + UPI_PIN + ";" ;
		ResultSet rs=stmt.executeQuery(s);
		
		if(!rs.next()) {
			return false;
		}else {
			return true;
		}
	}
	
	String balanceUpdation(int Amount, String Account_no) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK", "root", "Rohithdata*");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		Statement stmt1 = con.createStatement();
		String s = "UPDATE Bank_balance SET Balance = Balance-" + Amount + " WHERE Account_no = "+Account_no+";";
		String c = "SELECT Balance FROM Bank_balance WHERE Account_no = "+ Account_no+" and Balance > "+Amount+";";
		ResultSet rs=stmt.executeQuery(c);
		if(!rs.next()) {
			String statement = "INSUFFICIENT BALANCE!";
			return statement;
		}else {
			stmt1.executeUpdate(s);
			String statement = "TRANSACTION SUCCESSFUL!";
			return statement;
		}
		
	}
}
