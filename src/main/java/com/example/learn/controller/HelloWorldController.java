package com.example.learn.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.learn.model.UserDetails;

@RestController
@RequestMapping(value="/api/")
public class HelloWorldController {

	
	@Autowired
//	private ResourceBundleMessageSource messageSource;
//	@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Kalyan", "Reddy", "Hydrabad");
	}
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/get-greeting", method = RequestMethod.GET)
	public String greeting() {
	 return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());   
    }
	  
}
