package com.rest.hellowebservice.Udemy.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

	
	//using url param versioning 
	@GetMapping("v1/person")
	public PersonV1 personv1() {

		return new PersonV1("Pranav Roy");
	}
	@GetMapping("v2/person")
	public PersonV2 personv2() {

		return new PersonV2(new Name("Pranav", "Roy"));
	}
	
	//header versioning
	
	@GetMapping(value="/person/header",headers="API_PERSON_VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Pranav Roy");

		
	}
	@GetMapping(value="/person/header",headers="API_PERSON_VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Pranav", "Roy"));

		
	}
	
	
	//header versioning or minetypeversioning


	@GetMapping(value="/person/produce",produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Pranav Roy");

		
	}
	@GetMapping(value="/person/produce",produces="application/vnd.company.app-v2+json")
	public PersonV2  producesV2() {
		return new PersonV2(new Name("Pranav", "Roy"));

		
	}

}
