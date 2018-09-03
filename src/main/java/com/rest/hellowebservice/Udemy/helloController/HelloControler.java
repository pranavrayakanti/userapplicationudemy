package com.rest.hellowebservice.Udemy.helloController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
	
	@RequestMapping(method=RequestMethod.GET,path="/get")
	public String display()
	{
		
		return "hi";
		
	}
	@GetMapping(path="/get1")
	public String display1() {
		
		return "hi1";
	}
	
	@GetMapping(path="/getBean")
	public Hello displayBean() {
		
		return new Hello("Hi Bean Created");
	}
	
	@GetMapping(path="/getBean/{name}")
	public Hello displayBeanPathVariable(@PathVariable String name) {
		
		return new Hello(String.format("Hi Bean Created,%s", name));
	}
	
	

}
