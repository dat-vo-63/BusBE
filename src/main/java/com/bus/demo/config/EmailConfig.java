package com.bus.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.val;

@Configuration
public class EmailConfig {
@Value("$(spring.mail.host)")
private String mailHost;
@Value("$(spring.mail.port)")
private String mailPort;
@Value("$(spring.mail.username)")
private String mailUserName;
@Value("$(spring.mail.password)")
private String mailPassword;
}
