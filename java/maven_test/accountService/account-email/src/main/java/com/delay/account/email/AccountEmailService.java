package com.delay.account.email;

public interface AccountEmailService{
	void sendMail(String to,String subject,String htmlText) throws AccountEmailException;
}
