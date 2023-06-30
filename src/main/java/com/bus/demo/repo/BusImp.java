package com.bus.demo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.Bus;
import com.bus.demo.entity.Seat;
@Service
public class BusImp implements IBus {
	@Autowired
	BusRepo busRepo;

	@Override
	public boolean bookSeat(long id, List<Seat> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bus saveBus(Bus bus) {
		
		return busRepo.save(bus);
	}

	@Override
	public Bus findById(long id) {
		// TODO Auto-generated method stub
		return busRepo.findById(id);
	}

	@Override
	public List<Bus> findAll() {
	
		return busRepo.findAll();
	}

//	@Override
//	public boolean bookSeat(long id, List<Seat> list) {
//		Bus bus = busRepo.getById(id);
//		boolean check = false;
//		if (bus == null) {
//			check = false;
//		} else {
//
//			List<Seat> seats = bus.getSeats();
//			for (int i = 0; i < list.size(); i++) {
//				if (seats.contains(list.get(i))) {
//					check = false;
//					break;
//				} else {
//					seats.add(list.get(i));
//					bus.setSeats(seats);
//					busRepo.save(bus);
//					check = true;
//				}
//			}
//		}
//		return check;
//	}

}
