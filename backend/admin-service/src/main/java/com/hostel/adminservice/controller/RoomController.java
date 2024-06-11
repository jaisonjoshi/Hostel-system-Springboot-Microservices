package com.hostel.adminservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.adminservice.dto.CreateRoomDto;
import com.hostel.adminservice.exception.InsufficientRoomVacancyException;
import com.hostel.adminservice.exception.ResourceNotFoundException;
import com.hostel.adminservice.model.Room;
import com.hostel.adminservice.service.RoomService;





@RestController
@RequestMapping("/api/hostel/rooms")
public class RoomController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	private RoomService roomService;
	
	
	
	public RoomController(RoomService roomService) {
		super();
		this.roomService = roomService;
	}
	
	//Function to get the room details based on room number
	
	@GetMapping("/{roomNo}")
	public ResponseEntity<Room> getRoom(@PathVariable("roomNo") int roomNo) {
		return ResponseEntity.ok().body(roomService.getRoom(roomNo));
	}
	
	
	//Function to get the details of all available rooms
	
	@GetMapping("/")
	public ResponseEntity<List<Room>> getAllRooms(){
		return ResponseEntity.ok().body(roomService.getAllRooms());
	}
	
	
	//Function to create a room
	
	@PostMapping("/")
	public ResponseEntity<Room> createRoom(@RequestBody CreateRoomDto room) {
		return  ResponseEntity.created(null).body(roomService.createRoom(room));
	}
	
	
	//Function to update the room vacancy while registering a student
	
	@PutMapping("/{roomNo}")
	public int updateRoom(@PathVariable int roomNo) {
		try {
			return roomService.decrementRoomVacancy(roomNo);
		}
		catch(InsufficientRoomVacancyException e) {
			logger.error("This room is having no vacancy");
			throw e;
		}
		catch(ResourceNotFoundException e) {
			logger.error("Resource is not found");
			throw e;
		}
		
	}
	
	
	@PutMapping("/increment/{roomNo}")
	public boolean incrementRoomVacancy(@PathVariable int roomNo) {
		try {
			 roomService.incrementRoomVacancy(roomNo);
			 return true;
		}
		
		catch(Exception e) {
			logger.error("Resource is not found");
			throw e;
		}
		
	}
	
	
	
	//Function used to get the details of rooms having vacancy
	
	@GetMapping("/vacant")
	public ResponseEntity<List<Room>> getVacantRooms(){
		return ResponseEntity.ok().body(roomService.getVacantRooms());
	}
	
	
	//Function used to get rooms based on category (Eg. AC/NON-AC)
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Room>> getRoomsByCategory(@PathVariable String category){
		return ResponseEntity.ok().body(roomService.getRoomsByCategory(category));
	}
	
	//Function used to get rooms based on number of persons
	
	@GetMapping("/type/{capacity}")
	public ResponseEntity<List<Room>> getRoomsByCapacity(@PathVariable int capacity){
		return ResponseEntity.ok().body(roomService.getRoomsByCapacity(capacity));
	}
	
}
