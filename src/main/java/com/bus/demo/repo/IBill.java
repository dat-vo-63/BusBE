package com.bus.demo.repo;

import com.bus.demo.entity.Bill;

public interface IBill {
	public Bill addBill(String phone,long ticketId);
	public String Pay(long billId);
	public String CounterDown(long billId);
	public Bill findById(long billId);
	public String delete(long billId) ;
	}
