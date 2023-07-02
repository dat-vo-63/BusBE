package com.bus.demo.repo;

import java.util.List;
import java.util.Set;

import com.bus.demo.entity.Bus;
import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;

public interface ISchedule {
 public boolean bookSeat(long scheduleId,List<Seat> seat);
 public Schedual saveSchedual(Schedual schedual);
 public Schedual findScheduleByStartTime(String starttime);
 public Schedual findScheduleById(long id);
 public Schedual updateSchedule(long id,Schedual schedual);
 public List<String> getStartTime(long busId,String startDate);
 public List<Schedual> findScheduleByBusIdAndStartDateAndStartTime(long busId,String startDate,String startTime);
 public Set<Bus> findBusByStartDate();
 public List<String> getSeatBooked(long busId,String startDate,String startTime);
 public List<Schedual> findbyBusId(long busId);
 public String checkUpdate(long scheduleId);
}
