package com.chi.zhang.springboot.SpringDatabaseApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chi.zhang.springboot.SpringDatabaseApp.exceptions.ResourceNotFoundException;
import com.chi.zhang.springboot.SpringDatabaseApp.model.OrderModel;
import com.chi.zhang.springboot.SpringDatabaseApp.repository.OrderRepository;
import com.chi.zhang.springboot.SpringDatabaseApp.repository.SpringBootDataRepository;

@RestController
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private SpringBootDataRepository personRepo;
	
	@GetMapping("/person/{personId}/orders")
	public Page<OrderModel> getAllOrdersByPersonId(@PathVariable(value = "personId") Long personId, Pageable pageable){
		return orderRepo.findByPersonId(personId, pageable);
	}
	
	@PostMapping("/person/{personId}/newOrder")
	public OrderModel addOrder(@PathVariable(value = "personId") Long personId, @Valid @RequestBody OrderModel order) {
		return personRepo.findById(personId).map(SpringBootDataModel -> {
			order.setPerson(SpringBootDataModel);
			return orderRepo.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("PersonId","id", order));
	}
	
	@PutMapping("/person/{personId}/order/{orderId}")
	public OrderModel updateOrder(@PathVariable(value = "personId") Long personId,
		                          @PathVariable(value = "orderId") Long orderId,
		                          @Valid @RequestBody OrderModel orderDetails) 
	{
		if(!personRepo.existsById(personId)) {
			throw new ResourceNotFoundException("Person","id", personId);
		}
		return orderRepo.findById(orderId).map(order ->{
			order.setTitle(orderDetails.getTitle());
			order.setDescription(orderDetails.getDescription());
			return orderRepo.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId","id", orderId));
	}
	
	@DeleteMapping("/person/{personId}/order/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "personId") Long personId,
                                         @PathVariable(value = "orderId") Long orderId)
	{
		if(!personRepo.existsById(personId)) {
			throw new ResourceNotFoundException("Person","id", personId);
		}
		
		return orderRepo.findById(orderId).map(order ->{
			orderRepo.delete(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId","id", orderId));
	}

}
