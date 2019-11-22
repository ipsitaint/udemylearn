package com.example.learn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.learn.exception.UserNotFoundException;
import com.example.learn.model.Order;
import com.example.learn.service.OrderService;

@RestController
@RequestMapping(value="/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable("userid") Long userid) throws UserNotFoundException{
		try {
			return orderService.getAllOrders(userid);
		}catch(UserNotFoundException ux) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ux.getMessage());
		}
	}
	
	@PostMapping("/{userid}/orders")
	public  Order createOrder(@PathVariable("userid") Long userid, @RequestBody Order order) throws UserNotFoundException {
		try {
		return orderService.createOrder(userid, order);
		}catch(UserNotFoundException ux) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ux.getMessage());
		}
	}
	
//	@GetMapping("{userid}/orders/{orderid}")
//	public Optional<Order> getSpecificOrderUsingOrderId(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid){
//		try {
//			return orderService.getSpecificOrderUsingOrderId(userid,orderid);
//		}catch(UserNotFoundException ux) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ux.getMessage());
//		}
//	}
	
	@GetMapping("{userid}/orders/{orderid}")
	public Order getSpecificOrderUsingOrderId(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid){
		try {
			Optional<Order> orderOptional = orderService.getSpecificOrderUsingOrderId(userid,orderid);
			return orderOptional.get();
		}catch(UserNotFoundException ux) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ux.getMessage());
		}
	}
	
	
}
