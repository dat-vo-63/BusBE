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
	public boolean saveTicket(Ticket ticket) {
		List<Seat> seats = new ArrayList<>();
		boolean check = false;
//		Schedual schedual = null;
		for(int i =0;i<ticket.getSeats().size();i++)
		{
			Seat seat = repo2.findById(ticket.getSeats().get(i).getSeatId());
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
