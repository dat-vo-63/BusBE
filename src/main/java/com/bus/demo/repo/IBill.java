package com.bus.demo.repo;

import java.util.List;

import com.bus.demo.entity.Bill;
import com.bus.demo.entity.GetInfor;

public interface IBill {
	public Bill addBill(String phone,long ticketId);
	public String Pay(long billId);
	public String CounterDown(long billId);
	public Bill findById(long billId);
	public String delete(long billId) ;
	public List<GetInfor> getDetailBill();
	public GetInfor getDetailBillId(long billId);
	public List<GetInfor> findByBillIdlike(long billId);
	
	}
