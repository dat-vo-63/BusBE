package com.bus.demo.entity;

import java.util.List;

import lombok.Data;

@Data
public class GetInfor {
private long billId;
private String startDate;
private String startTime;
private List<String> seatNumber;
private int price;
public long getBillId() {
	return billId;
}
public void setBillId(long billId) {
	this.billId = billId;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public List<String> getSeatNumber() {
	return seatNumber;
}
public void setSeatNumber(List<String> seatNumber) {
	this.seatNumber = seatNumber;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
}
