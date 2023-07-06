package com.bus.demo.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.Bill;
import com.bus.demo.entity.GetInfor;
import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;
import com.bus.demo.entity.Ticket;
import com.bus.demo.entity.User;

@Service
public class BillService implements IBill {
	@Autowired
	UserRepo userRepo;
	@Autowired
	BillRepo billRepo;
	@Autowired
	TicketRepo ticketRepo;
	@Autowired
	SeatRepo seatRepo;
	@Autowired
	ScheduleRepo scheduleRepo;
	public static Thread thread = new Thread();

	@Override
	public Bill addBill(String phone, long ticketId) {
		Bill bill = new Bill();
		boolean a = false;
		User user = userRepo.findByPhoneNumber(phone);
		if (user == null) {
			a = false;
		} else {
			Ticket ticket = ticketRepo.findById(ticketId);
			if (ticket == null) {
				a = false;
			} else if (ticket.getBill() != null) {
				a = false;
			} else {

				bill.setBillStatus("Not Pay");
				int total = 0;
				for (int i = 0; i <= ticket.getSeats().size() - 1; i++) {
					total += ticket.getSeats().get(i).getSeatPrice();
				}
				bill.setTotalPrice(total);
				bill.setUser(user);
				billRepo.save(bill);
				ticket.setBill(bill);
				ticketRepo.save(ticket);
				System.out.println(ticket.getTicketId());
				
//				bill.setTickets(ticket);

//				ticket.setBill(bill);
//				ticketRepo.save(ticket);
			}
//			CounterDown(ticketId);
		}
		return bill;
	}

	@Override
	public String Pay(long billId) {
		String a = null;
		Bill bill = billRepo.findByBillId(billId);
		int pay = bill.getUser().getUserBalance();
		if (pay >= bill.getTotalPrice()) {
			bill.getUser().setUserBalance(pay - bill.getTotalPrice());
			bill.setBillStatus("Paied");
			billRepo.save(bill);
			a = "Pay Success";
		} else {
			a = "You don't have enought money";
		}
		return a;
	}

	@Override
	public String CounterDown(long billId) {
		String check = null;
		
		try {
			for (int i = 120; i >= 0; i--) {
				thread.sleep(1000);
				System.out.println(i);
				if (i == 0) {
					Ticket ticket = ticketRepo.findByBillId(billId);
					System.out.println(ticket.getTicketId());
					Bill bill = billRepo.findByBillId(ticket.getBill().getBillId());
					System.out.println(bill.getBillId());
					int totalseatBook = ticket.getSeats().size();
					Schedual schedual = scheduleRepo.findByScheduleId(ticket.getSeats().get(0).getSchedual().getScheduleId());
					
					if (bill.getBillStatus().equalsIgnoreCase("Not Pay")) {
						
						for (int j = 0; j <= ticket.getSeats().size() - 1; j++) {
							Seat seat = ticket.getSeats().get(j);
							 seat.setTicket(null);
							 
							seatRepo.save(seat);
							
						}
						int deleticket = ticketRepo.deleteByBillId(ticket.getTicketId());
						int deletebill = billRepo.deleteByBillId(bill.getBillId());
						System.out.println(deleticket);
						System.out.println(deletebill);
						schedual.setSeatLeft(schedual.getSeatLeft()+totalseatBook);
						scheduleRepo.save(schedual);

						check = "Deleted";
					} else {
						check = "Have Payed";
					}

				}
			}
		} catch (Exception e) {
			check = "Error";
		}

		return check;
	}

	@Override
	public Bill findById(long billId) {
		// TODO Auto-generated method stub
		return billRepo.findByBillId(billId);
	}

	@Override
	public String delete(long billId) {
		billRepo.deleteById(billId);
		return "Deleted";
	}

	@Override
	public List<GetInfor> getDetailBill() {
		List<Ticket> tickets = ticketRepo.findAll();
		
		List<GetInfor> getInfors = new ArrayList<>();
		for(int i=0;i<=tickets.size()-1;i++)
		{
			List<Seat> seatNo = new ArrayList<>();
			GetInfor getInfor = new GetInfor();
			getInfor.setBillId(tickets.get(i).getBill().getBillId());
			getInfor.setStartDate(tickets.get(i).getSeats().get(0).getSchedual().getStartDate());
			getInfor.setPrice(tickets.get(i).getBill().getTotalPrice());
			getInfor.setStartTime(tickets.get(i).getSeats().get(0).getSchedual().getStartTime());
			getInfor.setUserName(tickets.get(i).getBill().getUser().getUserName());
			getInfor.setUserPhone(tickets.get(i).getBill().getUser().getPhoneNumber());
			getInfor.setDeparture(tickets.get(i).getSeats().get(0).getSchedual().getDeparture());
			getInfor.setDestination(tickets.get(i).getSeats().get(0).getSchedual().getDestinations());
			getInfor.setStatus(tickets.get(i).getBill().getBillStatus());
			getInfor.setBusName(tickets.get(i).getSeats().get(0).getSchedual().getBus().getName());
			for(int j=0;j<=tickets.get(i).getSeats().size()-1;j++) {
				seatNo.add(tickets.get(i).getSeats().get(j));
			}
			getInfor.setSeatNumber(seatNo);
			getInfors.add(getInfor);
			
		}
		return getInfors;
		
	}

	@Override
	public GetInfor getDetailBillId(long billId) {
//		Bill bill =billRepo.findByBillId(billId);
		List<Seat> seatNo = new ArrayList<>();
		Ticket ticket= ticketRepo.findByBillId(billId);
		GetInfor getInfor = new GetInfor();
		getInfor.setBillId(ticket.getBill().getBillId());
		getInfor.setStartDate(ticket.getSeats().get(0).getSchedual().getStartDate());
		getInfor.setPrice(ticket.getBill().getTotalPrice());
		getInfor.setStartTime(ticket.getSeats().get(0).getSchedual().getStartTime());
		getInfor.setUserName(ticket.getBill().getUser().getUserName());
		getInfor.setUserPhone(ticket.getBill().getUser().getPhoneNumber());
		getInfor.setDeparture(ticket.getSeats().get(0).getSchedual().getDeparture());
		getInfor.setDestination(ticket.getSeats().get(0).getSchedual().getDestinations());
		getInfor.setStatus(ticket.getBill().getBillStatus());
		getInfor.setBusName(ticket.getSeats().get(0).getSchedual().getBus().getName());
		for(int j=0;j<=ticket.getSeats().size()-1;j++) {
			seatNo.add(ticket.getSeats().get(j));
		}
		getInfor.setSeatNumber(seatNo);
		return  getInfor;
	}

}