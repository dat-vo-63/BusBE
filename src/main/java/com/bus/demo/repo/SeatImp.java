package com.bus.demo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.Seat;

@Service
public class SeatImp implements ISeat {
@Autowired
SeatRepo repo;
	@Override
	public List<Seat> findByTicketId(long id) {
		return repo.findByTicketId(id);
	}

}
