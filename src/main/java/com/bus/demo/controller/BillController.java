package com.bus.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.demo.entity.Bill;
import com.bus.demo.entity.GetInfor;
import com.bus.demo.repo.IBill;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.IUser;
import com.bus.demo.repo.ScheduleService;
import com.bus.demo.repo.TicketService;
@RestController
@CrossOrigin(value = "*")
public class BillController {
	@Autowired
	IBus busRepo;
	@Autowired
	IBus Ibus;
	@Autowired
	ScheduleService schedule;
	@Autowired
	TicketService ticketService;
	@Autowired
	ISeat iSeat;
	@Autowired
	IBill iBill;
	@Autowired
	IUser iUser;
	@PostMapping("/addBill")
	public Bill addBill(@RequestBody Map<String, String> input)
	{
		
		return iBill.addBill((input.get("phoneNumber")),Long.parseLong(input.get("ticketId")));
	}
	@PostMapping("/count-down")
	public String countdown(@RequestBody Map<String, Long> map)
	{
		return iBill.CounterDown(map.get("billId"));
	}
	@GetMapping("/find-bill/{id}")
	public Bill findBillById(@PathVariable long id)
	{
		return iBill.findById(id);
	}
	@DeleteMapping("/delete")
	public String deleted(@RequestBody Bill bill)
	{
		return iBill.delete(bill.getBillId());
	}
	@PostMapping("/payment/{billId}")
	public String payment(@PathVariable Long billId) {
		return iBill.Pay(billId);
	}
	@GetMapping("/find-Bill")
	public List<GetInfor> findInfo() {
		return iBill.getDetailBill();
	}
	@GetMapping("/get-detail-bill/{billId}")
	public GetInfor findDetailBybillId(@PathVariable("billId") long billId)
	{
		return iBill.getDetailBillId(billId);
	}
	@PutMapping("/get-bill")
	public List<GetInfor> findBill(@RequestBody Map<String, String> map){
		return iBill.findByBillIdlike(Long.parseLong(map.get("billId")));
	}
}
