package com.chi.zhang.springboot.ChiVD.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chi.zhang.springboot.ChiVD.exceptions.DVDAlreadyRentedException;
import com.chi.zhang.springboot.ChiVD.exceptions.ResourceNotFoundException;
import com.chi.zhang.springboot.ChiVD.model.DVDModel;
import com.chi.zhang.springboot.ChiVD.model.UserModel;
import com.chi.zhang.springboot.ChiVD.repository.DVDRepository;
import com.chi.zhang.springboot.ChiVD.repository.UserRepository;


@RestController
@RequestMapping("/api")
public class Controller {
	
	@Autowired
	 UserRepository userRepo;
	
	//create user
	@PostMapping("/newuser")
	public UserModel createUser(@Valid @RequestBody UserModel user) {
		return userRepo.save(user);
	}
	
	//get user
	@GetMapping("/user/{id}")
	public UserModel getUserById(@PathVariable(value = "id")Long userId) {
		return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
	}
	
	//get all users
	@GetMapping("/user")
	public List<UserModel> getAllUsers() {
		return userRepo.findAll();
	}
	
	//update user
	@PutMapping("/user/{id}")
	public UserModel updateUser(@PathVariable(value = "id")Long userId, @Valid @RequestBody UserModel userDetails) {
		
		UserModel user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setName(userDetails.getName());
		user.setAddress(userDetails.getAddress());
		user.setAge(userDetails.getAge());
		
		UserModel update = userRepo.save(user);
		return update;
	}
	
	//delete user
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id")Long userId){
		
		UserModel user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		userRepo.delete(user);
		return ResponseEntity.ok().build();
	}
	
	@Autowired
	DVDRepository dvdRepo;
	
	//create DVD
	@PostMapping("/newdvd")
	public DVDModel createUser(@Valid @RequestBody DVDModel dvd) {
		return dvdRepo.save(dvd);
	}
	
	//get DVD
	@GetMapping("/dvd/{id}")
	public UserModel getDVDById(@PathVariable(value = "id")Long dvdId) {
		return userRepo.findById(dvdId).orElseThrow(()-> new ResourceNotFoundException("User","id",dvdId));
	}
	
	//get all DVDs
	@GetMapping("/dvd")
	public List<DVDModel> getAllDVDs() {
		return dvdRepo.findAll();
	}
	
	//rent DVD
	@PutMapping("/dvd/{dvdId}/{userId}")
	public DVDModel rentDVD(@PathVariable(value = "dvdId")Long dvdId,
				            @PathVariable(value = "userId")Long userId, 
				            @Valid @RequestBody DVDModel rentInfo) 
	{	

		UserModel user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		DVDModel dvd = dvdRepo.findById(dvdId).orElseThrow(()-> new ResourceNotFoundException("DVD","id",dvdId));
		
		if(dvd.getRental_date() == null) {			
		    dvd.setRental_date(new Date());
		    dvd.setRented_by_user(user.getId());			
		    DVDModel update = dvdRepo.save(dvd);
		return update;
		}
		else {
			throw(new DVDAlreadyRentedException(dvd.getDvd_id(),dvd.getRented_by_user()));			
		}
	}
	
	//return DVD
	@PutMapping("/dvd/{dvdId}")
	public DVDModel returnDVD(@PathVariable(value = "dvdId")Long dvdId, @Valid @RequestBody DVDModel rentInfo) {		
		DVDModel dvd = dvdRepo.findById(dvdId).orElseThrow(()-> new ResourceNotFoundException("DVD","id",dvdId));
		dvd.setRental_date(null);
		dvd.setRented_by_user(null);			
		DVDModel update = dvdRepo.save(dvd);
		return update;
	}

}
