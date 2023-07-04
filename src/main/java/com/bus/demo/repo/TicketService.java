package com.bus.demo.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;
import com.bus.demo.entity.Ticket;

@Service
public class TicketService implements ITicket{
@Autowired
TicketRepo repo;
@Autowired
SeatRepo repo2;
	@Override
	public boolean saveTicket(List<Long> seatId) {
		List<Seat> seats = new ArrayList<>();
		boolean check = false;
		Ticket ticket = new Ticket();
//		Schedual schedual = null;
		for(int i =0;i<seatId.size();i++)
		{
			Seat seat = repo2.findBySeatId(seatId.get(i)) ;
			seat.getSchedual().setSeatLeft(seat.getSchedual().getSeatLeft()-1);
			if(seat.getTicket()==null)
			{
			
			seat.setTicket(ticket);
			check = true;
			seats.add(seat);
			
			}
			else
			{
				check =false;
				break;
			}
		}
		if(check)
		{
		ticket.setSeats(seats);
	
		repo.save(ticket);
		}
		return check;
		
	}
	

}
