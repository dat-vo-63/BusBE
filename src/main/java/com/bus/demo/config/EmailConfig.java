//package com.bus.demo.config;
//
//import java.util.Properties;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import lombok.val;
//
//@Configuration
//public class EmailConfig {
//@Value("${spring.mail.host}")
//private static String mailHost;
//@Value("${spring.mail.port}")
//private static String mailPort;
//@Value("${spring.mail.username}")
//private static String mailUserName;
//@Value("${spring.mail.password}")
//private static String mailPassword;
//
//@Bean
//private   static JavaMailSender getMailSender() {
//	JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
//	javaMailSenderImpl.setPort(Integer.parseInt(mailPort));
//	javaMailSenderImpl.setHost(mailHost);
//	javaMailSenderImpl.setUsername(mailUserName);
//	javaMailSenderImpl.setPassword(mailPassword);	
//	Properties properties = javaMailSenderImpl.getJavaMailProperties();
//	properties.put("mail.smtp.starttls.enable", "true");	
//	return javaMailSenderImpl;
//}
//}
