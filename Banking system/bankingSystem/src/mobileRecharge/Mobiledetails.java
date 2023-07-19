package mobileRecharge;

public class Mobiledetails{
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

