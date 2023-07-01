package com.bus.demo.repo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.Bus;
import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;

@Service
public class ScheduleService implements ISchedule{
@Autowired
ScheduleRepo repo;
@Autowired 
BusRepo busRepo;
@Autowired
SeatRepo seatRepo;
	@Override
	public boolean bookSeat(long scheduleId, List<Seat> seat) {
//		Schedual schedual = repo.findById(scheduleId);
//		boolean check = false;
//		if(schedual ==null)
//		{
//			check= false;
//		}
//		else
//		{
//			List<Seat> list = schedual.getSeats();
//			for(int i=0;i < seat.size();i++ )
//			{
//				if(list.contains(seat.get(i)))
//				{
//					check = false;
//					break;
//				}
//				else
//				{
//					list.add(seat.get(i));
//					schedual.setSeats(list);
//					schedual.setSeatLeft(schedual.getTotalSeat()-schedual.getSeats().size());
//					repo.save(schedual);
//					check = true;
//				}
//			}
//		}
//		return check;
		return true;
	}
	@Override
	public Schedual saveSchedual(Schedual schedual) {
		List<Seat> seats = new ArrayList<>();
		Bus bus = busRepo.findById(schedual.getBus().getBusId());
		
		if (bus == null)
		{
			System.out.println("null");
		}
		else {
			schedual.setTotalSeat(bus.getSeat());
			schedual.setSeatLeft(bus.getSeat());
		for(int i=1;i<=schedual.getTotalSeat();i++)
		{
			Seat seat = new Seat();
			seat.setSeatNo(Integer.toString(i));
			seat.setSchedual(schedual);
			if(i >= 90)
			{
				seat.setSeatPrice(90000);
			}
			else
			{
				seat.setSeatPrice(50000);
			}
			seatRepo.save(seat);
			seats.add(seat);
		}
		schedual.setSeats(seats);
		schedual.setBus(bus);
		}
		
		return repo.save(schedual);
	}
	@Override
	public Schedual findScheduleByStartTime(String starttime) {
		Schedual list= repo.getSchedualsByStartTime(starttime);
		
			
		return list;
	}
	@Override
	public Schedual updateSchedule(long id,Schedual schedual) {
		Schedual schedual2 = null; 
			schedual2 =	repo.findById(id);
			if(schedual2 == null)
			{}
			else
			{
				schedual2.setStartDate(schedual.getStartDate());
				schedual2.setStartTime(schedual.getStartTime());
				schedual2.setTotalSeat(schedual.getTotalSeat());
				schedual2.setSeatLeft(schedual.getSeatLeft());
				schedual2.setEndTime(schedual.getEndTime());
				repo.save(schedual2);
			}
		return schedual2;
	}
	@Override
	public List<String> getStartTime(long busId, String startDate) {
		Date  currentDate = new Date();
		System.out.println(currentDate);
		LocalDateTime dateTime2 = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
		LocalDate dateTimeGetDate = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(dateTimeGetDate);
		System.out.println(dateTime2.format(DateTimeFormatter.ofPattern("HH.mm")));
		double check = Double.parseDouble(dateTime2.format(DateTimeFormatter.ofPattern("HH.mm")));
		List<String> list= repo.getStartTimeByBusIdAndStartDate(busId, startDate);
		List<String> newList=new ArrayList<>();
		for(int i=0;i<=list.size()-1;i++) {
			String a= list.get(i).replace(':', '.');
			double compare = Double.parseDouble(a);
			if(check<compare)
			{
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	@Override
	public List<Schedual> findScheduleByBusIdAndStartDateAndStartTime(long busId, String startDate, String startTime) {
		
		return repo.findByBusIdAndStartDateAndStartTime(busId, startDate, startTime);
	}
	@Override
	public Schedual findScheduleById(long id) {
		
		return repo.findById(id);
	}
	@Override
	public Set<Bus> findBusByStartDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String a = dateFormat.format(date);
		System.out.println(a);
		List<Schedual> scheduals= repo.findByStartDate(a);
		Set<Bus> bus = new HashSet<>();
		for(int i=0;i<scheduals.size();i++) {
			bus.add(scheduals.get(i).getBus());
		}
		return bus;
	}
	@Override
	public List<String> getSeatBooked(long busId,String startDate,String startTime) {
		List<Schedual> scheduals = findScheduleByBusIdAndStartDateAndStartTime(busId, startDate, startTime);
		List<Seat> seats = scheduals.get(0).getSeats();
		List<Seat> seats2 = new ArrayList<>();
		List<String> list = new ArrayList<>();
		for(int i =0;i<=seats.size()-1;i++) {
			if(seats.get(i).getTicket()!=null)
			{
				seats2.add(seats.get(i));
			}
		}
		for(int j=0;j<=seats2.size()-1;j++) {
			list.add(seats.get(j).getSeatNo());
		}
		return list;
	}

}
