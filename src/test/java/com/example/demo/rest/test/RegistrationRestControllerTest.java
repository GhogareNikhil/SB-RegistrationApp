package com.example.demo.rest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockRequestDispatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.bindings.User;
import com.example.demo.constatnts.AppConstants;
import com.example.demo.rest.RegistrationRestController;
import com.example.demo.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = RegistrationRestController.class)
public class RegistrationRestControllerTest {

	@MockBean
	private RegistrationService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void emilCheckTest1() throws Exception {
		when(service.uniqeEmail("nk@gmail.com")).thenReturn(true);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emailCheck/nk@gmail.com"); // preparing the
																										// request
		MvcResult mockResult = mockMvc.perform(builder).andReturn(); // sending request
		MockHttpServletResponse response = mockResult.getResponse(); // geting response

		String resBody = response.getContentAsString(); // geting responseBody
		assertEquals(AppConstants.UNIQUE, resBody);
	}

	@Test
	public void emilCheckTest2() throws Exception {
		when(service.uniqeEmail("ak@gmail.com")).thenReturn(false);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emailCheck/ak@gmail.com"); // preparing the
		MvcResult mockResult = mockMvc.perform(builder).andReturn(); // sending request
		MockHttpServletResponse response = mockResult.getResponse(); // geting response

		String resBody = response.getContentAsString(); // geting responseBody
		assertEquals(AppConstants.DUPLICATE, resBody);
	}

	@Test
	public void countriesTest() throws Exception {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "India");
		map.put(2, "USA");

		when(service.getCountries()).thenReturn(map);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/countries");
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();

		int status = response.getStatus();
		assertEquals(200, status);

	}
	
	@Test
	public void statesTest() throws Exception {
		Map<Integer, String> map= new HashMap<>();
		map.put(1, "Maharastra");
		map.put(1, "Delhi");
		
		when(service.getCountries()).thenReturn(map);
	
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				                                     .get("/states/1");
		MvcResult mvcresult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcresult.getResponse();
		
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void citiesTest() throws Exception {
		Map<Integer, String> map= new HashMap<>();
		map.put(1, "Mumbai");
		map.put(1, "Pune");
		
		when(service.getCountries()).thenReturn(map);
	
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				                                     .get("/cities/1");
		MvcResult mvcresult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcresult.getResponse();
		
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void saveUserTest1() throws Exception {
		User user= new User();
		user.setUserFname("Nikhil");
		user.setUserLname("gorge");
		user.setUserEmaill("nk@gmail.com");
		
		when(service.registerUser(user)).thenReturn(true);
		
		ObjectMapper mapper = new ObjectMapper();
		String string = mapper.writeValueAsString(user);
		
        MockHttpServletRequestBuilder reuest =MockMvcRequestBuilders.post("/saveUser")
                                        .contentType("application/json")
                                        .content(string);
        MvcResult result = mockMvc.perform(reuest).andReturn();
        String resBody = result.getResponse().getContentAsString();
        assertEquals(AppConstants.SUCCESS, resBody);
	}
	
	@Test
	public void saveUserTest2() throws Exception {
		User user= new User();
		user.setUserFname("Nikhil");
		user.setUserLname("gorge");
		user.setUserEmaill("nk@gmail.com");
		
		when(service.registerUser(user)).thenReturn(false);
		
		ObjectMapper mapper = new ObjectMapper();
		String string = mapper.writeValueAsString(user);
		
        MockHttpServletRequestBuilder reuest =MockMvcRequestBuilders.post("/saveUser")
                                        .contentType("application/json")
                                        .content(string);
        MvcResult result = mockMvc.perform(reuest).andReturn();
        String resBody = result.getResponse().getContentAsString();
        assertEquals(AppConstants.FAIL, resBody);
                  
	}
	
	
	
	

}
