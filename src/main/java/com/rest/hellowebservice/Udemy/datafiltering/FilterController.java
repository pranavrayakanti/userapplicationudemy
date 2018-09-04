package com.rest.hellowebservice.Udemy.datafiltering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {

	@GetMapping(path = "/static")
	public List<UserFilter> staticfilter() {
		return Arrays.asList(new UserFilter("1", "pranav", "pwd"), new UserFilter("2", "ravi", "pwd2"));

	}
	
	
	@GetMapping(path = "/dynamic")
	public MappingJacksonValue dynamicfilter() {
		List<UserFilter>userdata= Arrays.asList(new UserFilter("1", "pranav", "pwd"), new UserFilter("2", "ravi", "pwd2"));

		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("id");
		FilterProvider filters=new SimpleFilterProvider().addFilter("dynamicfilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(userdata);
		
		mapping.setFilters(filters);
		return mapping;
	}

}

