package com.chi.zhang.springboot.SpringDatabaseApp.controller;

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

import com.chi.zhang.springboot.SpringDatabaseApp.exceptions.ResourceNotFoundException;
import com.chi.zhang.springboot.SpringDatabaseApp.model.SpringBootDataModel;
import com.chi.zhang.springboot.SpringDatabaseApp.repository.SpringBootDataRepository;

@RestController
@RequestMapping("/api")
public class SpringBootDataController {
	
	@Autowired
	SpringBootDataRepository myRepo;
	
	//create person
	@PostMapping("/SpringBootDataModel")
	public SpringBootDataModel createPerson(@Valid @RequestBody SpringBootDataModel sBDM) {
		return myRepo.save(sBDM);
	}
	
	//get person
	@GetMapping("/person/{id}")
	public SpringBootDataModel getPersonById(@PathVariable(value = "id")Long personId) {
		return myRepo.findById(personId).orElseThrow(()-> new ResourceNotFoundException("SpringBootDataModel","id",personId));
	}
	
	//get all people
	@GetMapping("/person")
	public List<SpringBootDataModel> getAllPeople() {
		return myRepo.findAll();
	}
	
	//update person
	@PutMapping("/person/{id}")
	public SpringBootDataModel updatePerson(@PathVariable(value = "id")Long personId, @Valid @RequestBody SpringBootDataModel personDetails) {
		
		SpringBootDataModel sBDM = myRepo.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Person","id",personId));
		sBDM.setName(personDetails.getName());
		sBDM.setAddress(personDetails.getAddress());
		sBDM.setAge(personDetails.getAge());
		
		SpringBootDataModel update = myRepo.save(sBDM);
		return update;
	}
	
	//delete person
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id")Long personId){
		
		SpringBootDataModel sBDM = myRepo.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Person","id",personId));
		myRepo.delete(sBDM);
		return ResponseEntity.ok().build();
	}

}
