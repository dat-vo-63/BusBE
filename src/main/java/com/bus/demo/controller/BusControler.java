package com.bus.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.demo.entity.Bill;
import com.bus.demo.entity.Bus;
import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;
import com.bus.demo.entity.Ticket;
import com.bus.demo.entity.User;
import com.bus.demo.repo.BusRepo;
import com.bus.demo.repo.IBill;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISchedule;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.IUser;
import com.bus.demo.repo.ScheduleService;
import com.bus.demo.repo.TicketService;

@RestController
@RequestMapping()
public class BusControler {
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
//@GetMapping ("/all")
//public ResponseEntity<List<Bus>>  getAll(){
//	return new ResponseEntity<>(IBus.findAll(),HttpStatus.OK);
//}
	@PostMapping("/add")
	public Bus addBus(@RequestBody Bus bus)
	{
		return busRepo.saveBus(bus);
	}
	@GetMapping("/findBus")
	public Set<Bus> findBusToCurrentDate()
	{
	return (schedule.findBusByStartDate());
	}
	
	@GetMapping("/{id}")
	public Optional<Bus> findAllSeat(@PathVariable long id)
	{
	return Optional.of(busRepo.findById(id));
	}
	@PostMapping("/add/schedule")
	public Schedual addSche(@RequestBody Schedual schedual) {
		return  schedule.saveSchedual(schedual);
	}
	@GetMapping("/get-schedule-start-time")
	public Schedual getScheduleByStartTime(@RequestBody Schedual schedual) {
		return  schedule.findScheduleByStartTime(schedual.getStartTime());
	}
	@GetMapping("/get/{id}")
	public Schedual getSchedule(@PathVariable long id) {
		return schedule.findScheduleById(id);
	}
	@GetMapping("/get-schedule-bus-id-start-time-start-date/{id}")
	public List<Schedual> getScheduleByBusIdAndStartDateAndStartTime(@PathVariable long id,@RequestBody Schedual schedual) {
			return schedule.findScheduleByBusIdAndStartDateAndStartTime(id, schedual.getStartDate(), schedual.getStartTime());
	}
	@PostMapping("/update/{id}")
	public Schedual updateSchedule(@RequestBody Schedual schedual,@PathVariable long id) {
		return  schedule.updateSchedule(id, schedual);
	}
	@GetMapping("/get-start-time/{id}")
	public List<String> getStartTime(@RequestBody Schedual schedual,@PathVariable long id) {
		return  schedule.getStartTime(id,schedual.getStartDate());
	}
	@PostMapping ("/add-ticket")
	public String  addTicket(@RequestBody Ticket ticket) {
		return ticketService.saveTicket(ticket)?"Add Success":"Sorry seat already booked";
	
	}
	@GetMapping("/find-seat/{id}")
	public List<Seat> findSeatByTicket(@PathVariable long id)
	{
		return iSeat.findByTicketId(id);
	}
	@PostMapping("/addBill")
	public Bill addBill(@RequestBody Map<String, String> input)
	{
		return iBill.addBill(Integer.parseInt(input.get("phoneNumber")),Long.parseLong(input.get("ticketId")));
	}
	@PostMapping ("/add-user")
	public User addUser(@RequestBody User user) {
		return iUser.save(user);
	}
	@PostMapping("/count-down/{id}")
	public String countdown(@PathVariable int id)
	{
		return iBill.CounterDown(id);
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
	}
