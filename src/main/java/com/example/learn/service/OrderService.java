package com.example.learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learn.exception.UserNotFoundException;
import com.example.learn.model.Order;
import com.example.learn.model.User;
import com.example.learn.repository.OrderRepository;
import com.example.learn.repository.UserRepository;

@Service
public class OrderService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders(Long userid) throws UserNotFoundException{ 
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		return userOptional.get().getOrder();
//		return orderRepository.findAll();
	}
	
	public Order createOrder(Long userid, Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
	}
	
	public Optional<Order> getSpecificOrderUsingOrderId(Long userid, Long orderid) throws UserNotFoundException{
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}else {
			Optional<Order> orderOptional = orderRepository.findById(orderid);
			if(!orderOptional.isPresent()) {
				throw new UserNotFoundException("Order not found");
			}else {
				return orderOptional;
			}
		}
	}
}
