package Invoice;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Invoice {
	
	public void createInvoice(String account_no, String bank, int amount, String time) {
		try {
	        File invoice = new File("C:\\Users\\rohith vishwa\\Desktop\\invoice.txt");
	        if(invoice.exists()) {
	        	invoice.delete();
	        	File invoice1 = new File("C:\\Users\\rohith vishwa\\Desktop\\invoice.txt");
	        	invoice1.createNewFile();
	        	FileWriter writer = new FileWriter(invoice1);
	        	writer.write("----------------------------------------------\n");
	        	writer.write("\t\tINVOICE\n");
	        	writer.write("----------------------------------------------\n");
	        	writer.write("₹ "+amount+ " PAID FROM ACCOUNT " + account_no);
	        	writer.write("\n");
	        	writer.write(time);
	        	writer.write("\n\n");
	        	writer.write("PAYMENT MADE BY PAYPOINT INC.");
	        	writer.close();
	        }else {
	        	invoice.createNewFile();
	        	FileWriter writer = new FileWriter(invoice);
	        	writer.write("----------------------------------------------\n");
	        	writer.write("\t\tINVOICE\n");
	        	writer.write("----------------------------------------------\n");
	        	writer.write("₹"+amount+ " PAID FROM ACCOUNT " + account_no);
	        	writer.write("\n");
	        	writer.write(time);
	        	writer.write("\n\n");
	        	writer.write("PAYMENT MADE BY PAYPOINT INC.");
	        	writer.close();
	        	
	        }
	    }
	    catch (IOException e) {
	        System.out.println("An error has occurred.");
	    }
	}	
}

