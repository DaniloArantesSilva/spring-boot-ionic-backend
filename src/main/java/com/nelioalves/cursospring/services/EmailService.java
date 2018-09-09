package com.nelioalves.cursospring.services;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursospring.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
