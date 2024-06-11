package com.hostel.paymentservice.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column
	private long studentid;
	
	@Column 
	private String name;
	
	
	@Column
	private long roomId;
	
	@Column
	private int roomNo;
	
	@Column
	private int capacity;
	
	@Column
	private String category;
	
	@Column
	private LocalDate date;
	
	@Column
	private double amount;
	@Column
	private int month;
	@Column
	private int year;
	@Column
	private boolean status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	public Payment() {
		super();
	}
	
	
	public Payment(long studentid, String name, long roomId, int roomNo, int capacity, String category, LocalDate date,
			double amount, int month, int year, Boolean status) {
		super();
		this.studentid = studentid;
		this.name = name;
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.capacity = capacity;
		this.category = category;
		this.date = date;
		this.amount = amount;
		this.month = month;
		this.year = year;
		this.status = status;
	}
	
	
	
	
	
	
	
	
}
